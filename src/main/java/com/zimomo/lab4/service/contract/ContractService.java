package com.zimomo.lab4.service.contract;

import com.zimomo.lab4.dao.contract.ContractDao;
import com.zimomo.lab4.dao.contract.Contract_ItemDao;
import com.zimomo.lab4.dao.roles.CustomerDao;
import com.zimomo.lab4.dao.roles.SalesmanDao;
import com.zimomo.lab4.entity.contract.Contract;
import com.zimomo.lab4.entity.roles.Customer;
import com.zimomo.lab4.entity.roles.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Contract> getAllContract(){
        return contractDao.getAllContract();
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
}
