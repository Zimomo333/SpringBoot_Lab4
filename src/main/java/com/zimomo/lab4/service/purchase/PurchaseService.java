package com.zimomo.lab4.service.purchase;

import com.zimomo.lab4.dao.ItemDao;
import com.zimomo.lab4.dao.order.OrderDao;
import com.zimomo.lab4.dao.purchase.PurchaseDao;
import com.zimomo.lab4.dao.purchase.Purchase_ItemDao;
import com.zimomo.lab4.entity.order.Order;
import com.zimomo.lab4.entity.purchase.Purchase;
import com.zimomo.lab4.entity.purchase.Purchase_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    Purchase_ItemDao purchase_itemDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ItemDao itemDao;

    public List<Purchase> getAllPurchase(){
        return purchaseDao.getAllPurchase();
    }

    public int addPurchase(String order_id,String date,String item_id,String quantity){
        //判断订单id正确性
        Order order=orderDao.findOrderById(Integer.parseInt(order_id));
        if(order==null)
            return 0;   //订单id无效

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String[] item_idArray=item_id.split(",");
        String[] quantityArray=quantity.split(",");
        HashSet<String> hashSet = new HashSet<String>();
        for(int i=0;i<item_idArray.length;i++){
            if(item_idArray[i].equals("-1"))
                return 1;    //未选商品
            if(!Pattern.matches("[0-9]*",quantityArray[i])||quantityArray[0].equals("0"))
                return 2;   //数量格式错误
            hashSet.add(item_idArray[i]);
        }
        if(hashSet.size()!=item_idArray.length)
            return 3;   //请勿重复选择商品

        //添加进货单
        purchaseDao.addPurchase(Integer.parseInt(order_id),format.parse(date,new ParsePosition(0)));
        int purchase_id=purchaseDao.getLastInsertId();
        for(int i=0;i<item_idArray.length;i++){
            purchase_itemDao.addPurchaseItem(purchase_id,Integer.parseInt(item_idArray[i]),Integer.parseInt(quantityArray[i]));
        }
        return 4;   //添加成功
    }

    //确认进货
    public void confirmPurchase(String purchase_id){
        Purchase purchase=purchaseDao.findPurchaseById(Integer.parseInt(purchase_id));
        for(Purchase_Item purchase_item : purchase.getPurchase_itemList()){
            itemDao.purchaseItem(purchase_item.getItem_Id(),purchase_item.getQuantity());
        }
        purchaseDao.confirmPurchase(Integer.parseInt(purchase_id));
    }
}
