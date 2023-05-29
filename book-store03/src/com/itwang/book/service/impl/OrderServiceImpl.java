package com.itwang.book.service.impl;

import com.itwang.book.dao.model.api.BookDao;
import com.itwang.book.dao.model.api.OrderDao;
import com.itwang.book.dao.model.api.OrderItemDao;
import com.itwang.book.dao.model.impl.BookDaoImpl;
import com.itwang.book.dao.model.impl.OrderDaoImpl;
import com.itwang.book.dao.model.impl.OrderItemDaoImpl;
import com.itwang.book.entity.Cart;
import com.itwang.book.entity.Order;
import com.itwang.book.entity.OrderItem;
import com.itwang.book.entity.User;
import com.itwang.book.service.api.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    private BookDao bookDao = new BookDaoImpl();
    /**
     * 查询数据库中的订单信息
     * @return
     */
    @Override
    public List<Order> getOrderList(Integer userId) {
        return orderDao.getOrderList(userId);
    }

    /**
     * 查询购物车中的订单以及用户id
     * @param cart
     * @param user
     * @return
     */
    @Override
    public String checkOrder(Cart cart, User user) {
        if (cart == null || cart.getCartItemList() == null || cart.getCartItemList().size() == 0 || user == null){
            throw new RuntimeException("系统错误，请按步骤重新操作！");
        }

        //2.保存order对象
        Order order = new Order();

        //3.生成订单号
        Date currentTime =  new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String createTime = simpleDateFormat.format(currentTime);
        //订单号
        String orderSequence = createTime + user.getUserId();


        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String realCreateTime = simpleDateFormat1.format(currentTime);


        order.setOrderSequence(orderSequence);

        order.setCreateTime(realCreateTime);

//        order.setTotalCount(cart.getTotalCount());

        order.setOrderStatus(0);

        order.setTotalAmount(cart.getTotalAmount());

        //4.从购物车对象，获取总数量
        order.setTotalCount(cart.getTotalCount());

        //5,从用户对象获取用户id
        order.setUserId(user.getUserId());

        //执行保存操作，获取自增主键
        Integer orderId = orderDao.insertOrder(order);

        //6.保存List<OrderItem>集合对象
        List<OrderItem> orderItemList = cart.getCartItemList().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();

            orderItem.setBookName(cartItem.getBook().getBookName());
            orderItem.setItemCount(cartItem.getCount());
            orderItem.setItemAmount(cartItem.getAmount());
            orderItem.setImgPath(cartItem.getBook().getImgPath());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItem.setOrderId(orderId.toString());
            return orderItem;
        }).collect(Collectors.toList());

        //对List<OrderItem> 集合对象执行批量保存
        orderItemDao.batchInsert(orderItemList);

        //更新图书库存
        bookDao.batchUpdateBook(cart.getCartItemList());

        //清空购物车
        cart.clearCart();

        return orderSequence;
    }
}
