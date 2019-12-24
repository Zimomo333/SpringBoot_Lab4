package com.zimomo.lab4.service.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zimomo.lab4.dao.contract.ContractDao;
import com.zimomo.lab4.dao.order.OrderDao;
import com.zimomo.lab4.dao.order.Order_ItemDao;
import com.zimomo.lab4.entity.contract.Contract;
import com.zimomo.lab4.entity.contract.Contract_Item;
import com.zimomo.lab4.entity.delivery.Delivery;
import com.zimomo.lab4.entity.delivery.Delivery_Item;
import com.zimomo.lab4.entity.order.Order;
import com.zimomo.lab4.entity.order.Order_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    Order_ItemDao order_itemDao;

    @Autowired
    ContractDao contractDao;

    public List<Order> getAllOrder(){
        return orderDao.getAllOrder();
    }

    //查询订单发了多少货
    public List<Order> OrderRest(List<Order> list){
        for(Order order:list){
            HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
            for (Delivery delivery : order.getDeliveryList()) {
                for(Delivery_Item delivery_item : delivery.getDelivery_itemList()){
                    if(hashMap.get(delivery_item.getItem().getItemName())==null){
                        hashMap.put(delivery_item.getItem().getItemName(),delivery_item.getQuantity());
                    }else
                        hashMap.put(delivery_item.getItem().getItemName(),hashMap.get(delivery_item.getItem().getItemName())+delivery_item.getQuantity());
                }
            }
            order.setHashMap(hashMap);
        }
        return list;
    }

    public void checkFinishOrder(String order_id){
        //查询订单发了多少货,是否等于订单采购数量
        Order order = orderDao.findOrderById(Integer.parseInt(order_id));
        HashMap<String,Integer> hashMap_check = new HashMap<String,Integer>();
        for (Delivery delivery : order.getDeliveryList()) {
            for(Delivery_Item delivery_item : delivery.getDelivery_itemList()){
                if(hashMap_check.get(delivery_item.getItem().getItemName())==null){
                    hashMap_check.put(delivery_item.getItem().getItemName(),delivery_item.getQuantity());
                }else
                    hashMap_check.put(delivery_item.getItem().getItemName(),hashMap_check.get(delivery_item.getItem().getItemName())+delivery_item.getQuantity());
            }
        }
        int finish=1;
        for(Order_Item order_item:order.getOrder_itemList()){
            if(hashMap_check.get(order_item.getItem().getItemName())==null) {   //已发货商品hash表中没有订单中的商品，证明未发货，订单未完成，跳出循环
                finish=0;
                break;
            }
            if(order_item.getQuantity()>hashMap_check.get(order_item.getItem().getItemName()))
                finish=0;
        }
        if(finish==1){
            orderDao.finishOrder(order.getOrder_Id());
        }
    }


    public int addOrder(String contract_id,String date,String item_id,String quantity){
        //判断合同id正确性
        Contract contract=contractDao.findContractById(Integer.parseInt(contract_id));
        if(contract==null)
            return 0;   //合同id无效

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String[] item_idArray=item_id.split(",");
        String[] quantityArray=quantity.split(",");
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        for(int i=0;i<item_idArray.length;i++){
            if(item_idArray[i].equals("-1"))
                return 1;    //未选商品
            if(!Pattern.matches("[0-9]*",quantityArray[i])||quantityArray[0].equals("0"))
                return 2;   //数量格式错误
            hashMap.put(item_idArray[i],Integer.parseInt(quantityArray[i]));
        }
        if(hashMap.size()!=item_idArray.length)
            return 3;   //请勿重复选择商品

        for(int i=0;i<item_idArray.length;i++) {
            for (Order order : contract.getOrderList()) {
                for(Order_Item order_item : order.getOrder_itemList()){
                    if(Integer.parseInt(item_idArray[i])==order_item.getItem_Id())
                        hashMap.put(item_idArray[i],order_item.getQuantity()+hashMap.get(item_idArray[i]));
                }
            }
        }
        for(Contract_Item contract_item:contract.getContract_itemList()){
            if(hashMap.get(Integer.toString(contract_item.getItem_Id()))==null)
                continue;
            if(contract_item.getQuantity()<hashMap.get(Integer.toString(contract_item.getItem_Id())))
                return 4;   //采购数量超出合同预定数量
//            if(contract_item.getQuantity()==hashMap.get(Integer.toString(contract_item.getItem_Id())))
//                contractDao.finishContract(contract.getContract_Id());  //完成合同
        }

        //添加订单
        orderDao.addOrder(Integer.parseInt(contract_id),format.parse(date,new ParsePosition(0)));
        int order_id=orderDao.getLastInsertId();
        for(int i=0;i<item_idArray.length;i++){
            order_itemDao.addOrderItem(order_id,Integer.parseInt(item_idArray[i]),Integer.parseInt(quantityArray[i]));
        }

        //更新总金额
        Order order=orderDao.findOrderById(order_id);
        double totalprice=0;
        for(Order_Item order_item : order.getOrder_itemList()){
            totalprice=order_item.getQuantity()*order_item.getItem().getItemPrice();
        }
        orderDao.updateTotalprice(order_id,totalprice);
        return 5;   //添加成功
    }

    public double dateAnalyse(String date_begin, String date_end){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Order> list = orderDao.dateAnalyse(format.parse(date_begin,new ParsePosition(0)),format.parse(date_end,new ParsePosition(0)));
        double total=0;
        for(Order order:list){
            total+=order.getTotalPrice();
        }
        return total;
    }
}
