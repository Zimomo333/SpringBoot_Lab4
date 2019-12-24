package com.zimomo.lab4.service.contract;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zimomo.lab4.dao.contract.ContractDao;
import com.zimomo.lab4.dao.contract.Contract_ItemDao;
import com.zimomo.lab4.dao.order.OrderDao;
import com.zimomo.lab4.dao.roles.CustomerDao;
import com.zimomo.lab4.dao.roles.SalesmanDao;
import com.zimomo.lab4.entity.contract.Contract;
import com.zimomo.lab4.entity.contract.Contract_Item;
import com.zimomo.lab4.entity.order.Order;
import com.zimomo.lab4.entity.order.Order_Item;
import com.zimomo.lab4.entity.roles.Customer;
import com.zimomo.lab4.entity.roles.LoginUser;
import com.zimomo.lab4.entity.roles.Salesman;
import com.zimomo.lab4.entity.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class ContractService {
    @Autowired
    ContractDao contractDao;

    @Autowired
    Contract_ItemDao contract_itemDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    SalesmanDao salesmanDao;

    @Autowired
    OrderDao orderDao;

    public List<Contract> getAllContract(){
        return contractDao.getAllContract();
    }

    public Contract findContractById(String contract_id){return  contractDao.findContractById(Integer.parseInt(contract_id));}

    public List<Contract> myContract(){
        LoginUserDetails loginUserDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginUser loginUser= loginUserDetails.getLoginUser();
        return contractDao.myContract(loginUser.getEmployee_Id());
    }

    //查询合同已经采购多少商品
    public List<Contract> ContractRest(List<Contract> list){
        for(Contract contract:list){
            HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
            for (Order order : contract.getOrderList()) {
                for(Order_Item order_item : order.getOrder_itemList()){
                    if(hashMap.get(order_item.getItem().getItemName())==null){
                        hashMap.put(order_item.getItem().getItemName(),order_item.getQuantity());
                    }else
                        hashMap.put(order_item.getItem().getItemName(),hashMap.get(order_item.getItem().getItemName())+order_item.getQuantity());
                }
            }
            contract.setHashMap(hashMap);
        }
        return list;
    }

    public void changeEdit(String contract_id){
        contractDao.changeEdit(Integer.parseInt(contract_id));
    }

    public void checkFinishContract(String order_id){
        Order temp= orderDao.findOrderById(Integer.parseInt(order_id));
        int contact_id =temp.getContract_Id();
        Contract contract = contractDao.findContractById(contact_id);
        int finish=1;
        //查询合同采购了多少商品,是否等于合同预订数量
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        for (Order order : contract.getOrderList()) {
            if(!order.getFinish())  //检验合同所属全部订单是否完成
                finish=0;
            for(Order_Item order_item : order.getOrder_itemList()){
                if(hashMap.get(order_item.getItem().getItemName())==null){
                    hashMap.put(order_item.getItem().getItemName(),order_item.getQuantity());
                }else
                    hashMap.put(order_item.getItem().getItemName(),hashMap.get(order_item.getItem().getItemName())+order_item.getQuantity());
            }
        }
        for(Contract_Item contract_item:contract.getContract_itemList()){
            if(hashMap.get(contract_item.getItem().getItemName())==null) {   //已采购商品hash表中没有合同中的商品，证明未采购，合同未完成，跳出循环
                finish=0;
                break;
            }
            if(contract_item.getQuantity()>hashMap.get(contract_item.getItem().getItemName()))      //合同某预定商品数量大于所有订单采购数量，未完成
                finish=0;
        }
        if(finish==1){
            contractDao.finishContract(contract.getContract_Id());
        }
    }

    public int addConotract(String sales_id,String customer_id,String date_begin,String date_end,String item_id,String quantity){
        //判断销售、客户id正确性
        Salesman salesman=salesmanDao.findSalesmanById(Integer.parseInt(sales_id));
        if(salesman==null)
            return 0;   //销售id无效
        Customer customer=customerDao.findCustomerById(Integer.parseInt(customer_id));
        if(customer==null)
            return 1;   //客户id无效

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date_begin_temp=format.parse(date_begin,new ParsePosition(0));
        Date date_end_temp=format.parse(date_end,new ParsePosition(0));

        if (date_begin_temp.after(date_end_temp))
            return 2;   //日期无效

        String[] item_idArray=item_id.split(",");
        String[] quantityArray=quantity.split(",");
        HashSet<String> hashSet = new HashSet<String>();
        for(int i=0;i<item_idArray.length;i++){
            if(item_idArray[i].equals("-1"))
                return 3;    //未选商品
            if(!Pattern.matches("[0-9]*",quantityArray[i])||quantityArray[0].equals("0"))
                return 4;   //数量格式错误
            hashSet.add(item_idArray[i]);
        }
        if(hashSet.size()!=item_idArray.length)
            return 5;   //请勿重复选择商品

        contractDao.addContract(Integer.parseInt(sales_id),Integer.parseInt(customer_id),date_begin_temp,date_end_temp);
        int contract_id=contractDao.getLastInsertId();
        for(int i=0;i<item_idArray.length;i++){
            contract_itemDao.addContractItem(contract_id,Integer.parseInt(item_idArray[i]),Integer.parseInt(quantityArray[i]));
        }
        return 6;   //添加成功
    }

    public int editContract(String contract_id,String sales_id,String customer_id,String date_begin,String date_end,String item_id,String quantity){
        //判断销售、客户id正确性
        Salesman salesman=salesmanDao.findSalesmanById(Integer.parseInt(sales_id));
        if(salesman==null)
            return 0;   //销售id无效
        Customer customer=customerDao.findCustomerById(Integer.parseInt(customer_id));
        if(customer==null)
            return 1;   //客户id无效

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date_begin_temp=format.parse(date_begin,new ParsePosition(0));
        Date date_end_temp=format.parse(date_end,new ParsePosition(0));

        if (date_begin_temp.after(date_end_temp))
            return 2;   //日期无效

        String[] item_idArray=item_id.split(",");
        String[] quantityArray=quantity.split(",");
        HashSet<String> hashSet = new HashSet<String>();
        for(int i=0;i<item_idArray.length;i++){
            if(item_idArray[i].equals("-1"))
                return 3;    //未选商品
            if(!Pattern.matches("[0-9]*",quantityArray[i])||quantityArray[0].equals("0"))
                return 4;   //数量格式错误
            hashSet.add(item_idArray[i]);
        }
        if(hashSet.size()!=item_idArray.length)
            return 5;   //请勿重复选择商品

        contractDao.editContract(Integer.parseInt(contract_id),Integer.parseInt(sales_id),Integer.parseInt(customer_id),date_begin_temp,date_end_temp);
        contract_itemDao.delContractItem(Integer.parseInt(contract_id));
        for(int i=0;i<item_idArray.length;i++){
            contract_itemDao.addContractItem(Integer.parseInt(contract_id),Integer.parseInt(item_idArray[i]),Integer.parseInt(quantityArray[i]));
        }
        return 6;   //修改成功
    }
}
