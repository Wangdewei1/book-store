package com.itwang.book.dao.model.impl;

import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.dao.model.api.OrderItemDao;
import com.itwang.book.entity.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements OrderItemDao {
    /**
     * 执行批量更新操作
     * 更新每个订单项
     * @param orderItemList
     */
    @Override
    public void batchInsert(List<OrderItem> orderItemList) {
        String sql = "insert into t_order_item(book_name,price,img_path,item_count,item_amount,order_id) values(?,?,?,?,?,?)";

        //创建二维数组
        Object[][] params = new Object[orderItemList.size()][6];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            params[i] = new Object[]{orderItem.getBookName(),orderItem.getPrice(),orderItem.getImgPath(),
            orderItem.getItemCount(),orderItem.getItemAmount(),orderItem.getOrderId()};
        }

        batchAllData(sql, params);
    }

    /**
     * 根据订单id查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> getAllOrderItemInfo(Integer orderId) {
        String sql = "select item_id,book_name bookName,price,img_path imgPath,item_count itemCount,item_amount itemAmount from t_order_item where order_id = ?";
        return selectAllData(sql, OrderItem.class, orderId);
    }
}
