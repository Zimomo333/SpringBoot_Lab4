package com.zimomo.lab4.service.delivery;

import com.zimomo.lab4.dao.ItemDao;
import com.zimomo.lab4.dao.delivery.DeliveryDao;
import com.zimomo.lab4.dao.delivery.DeliveryInfoDao;
import com.zimomo.lab4.dao.delivery.Delivery_ItemDao;
import com.zimomo.lab4.dao.order.OrderDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class DeliveryService {
    @Autowired
    DeliveryDao deliveryDao;

    @Autowired
    DeliveryInfoDao deliveryInfoDao;

    @Autowired
    Delivery_ItemDao delivery_itemDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ItemDao itemDao;

    public List<Delivery> getAllDelivery(){
        return deliveryDao.getAllDelivery();
    }

    public int addDelivery(String order_id,String date,String receiver,String telephone,String location,String item_id,String quantity){
        //判断订单id正确性
        Order order=orderDao.findOrderById(Integer.parseInt(order_id));
        if(order==null)
            return 0;   //订单id无效

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
            if(Integer.parseInt(quantityArray[i])>itemDao.findItemById(Integer.parseInt(item_idArray[i])).getResQuantity())
                return 4;   //发货数量超出库存数量
            //遍历订单的所有发货单，统计某商品已经发货了多少
            for (Delivery delivery : order.getDeliveryList()) {
                for(Delivery_Item delivery_item : delivery.getDelivery_itemList()){
                    if(Integer.parseInt(item_idArray[i])==delivery_item.getItem_Id())
                        hashMap.put(item_idArray[i],delivery_item.getQuantity()+hashMap.get(item_idArray[i]));
                }
            }
        }
        for(Order_Item order_item:order.getOrder_itemList()) {
            if (hashMap.get(Integer.toString(order_item.getItem_Id())) == null)
                continue;
            if (order_item.getQuantity() < hashMap.get(Integer.toString(order_item.getItem_Id())))
                return 5;   //发货数量超出订单订购数量
        }

        //添加发货单
        deliveryDao.addDelivery(Integer.parseInt(order_id),format.parse(date,new ParsePosition(0)),receiver,Integer.parseInt(telephone),location);
        int delivery_id=deliveryDao.getLastInsertId();
        for(int i=0;i<item_idArray.length;i++){
            delivery_itemDao.addDeliveryItem(delivery_id,Integer.parseInt(item_idArray[i]),Integer.parseInt(quantityArray[i]));
            itemDao.deliveryItem(Integer.parseInt(item_idArray[i]),Integer.parseInt(quantityArray[i]));
        }

        return 6;   //添加成功
    }

    public int confirmDelivery(String delivery_id, String postman, String telephone){
        Delivery delivery = deliveryDao.findDeliveryById(Integer.parseInt(delivery_id));
        if(delivery==null)
            return 0;   //发货单id无效


        deliveryDao.confirmDelivery(Integer.parseInt(delivery_id));
        deliveryInfoDao.addDeliveryInfo(Integer.parseInt(delivery_id),postman,telephone);
        return 1;   //确认成功
    }
}
