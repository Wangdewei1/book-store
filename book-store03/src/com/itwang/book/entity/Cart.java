package com.itwang.book.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 所有cartItem合起来  就是购物车
 */
public class Cart {
    //域中存的购物车
    public static final String CART_ATTR_NAME = "cart";
    //购物车集合
    //Integer 是 bookId CartItem 是购物车的每一条数据
    private Map<Integer,CartItem> cartItemMap;

    /**
     * 执行添加购物车操作
     */
    public void addCart(Book book,Integer count){
        //1.判断cartItemMap是否初始化了
        if (cartItemMap == null){
            cartItemMap = new HashMap<>();
        }
        //2.
        Integer bookId = book.getBookId();

        CartItem cartItem = cartItemMap.get(bookId);

        //4
        if (cartItem == null){
            cartItem = new CartItem(book, count);
            cartItemMap.put(bookId, cartItem);
        } else {
            int newCount = cartItem.getCount() + count;
            cartItem.setCount(newCount);
        }



    }

    /**
     * 计算 并返回购物车的总数量
     */
    public Integer getTotalCount(){
        if (cartItemMap == null || cartItemMap.size() == 0){
            return 0;
        }

        /**
         * 常规方法
         */
        /*        Integer sumCount = 0;
        Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            CartItem cartItem = entry.getValue();
            sumCount += cartItem.getCount();
        }
        return sumCount;*/


        /**
         * values ： 取map集合所有值组成的Collection集合  Collection<CartItem>
         * stream ：获取stream对象
         * map： 把Collection<CartItem>集合中的每一个CartItem对象映射为cartItem.getCount()
         * reduce： 调用reduce方法进行归纳汇总  返回一个Optional类型
         * ：： 方法引用
         * get(): 如果想要Optional中的值本身 ，就需要调用get方法
         */

        //完整lambda表达式的写法
        /*        cartItemMap.values().stream().map((CartItem cartItem) ->{
            return cartItem.getCount();
        });*/

        //完整的Integer::sum的写法
        /*        cartItemMap.values().stream().map(cartItem -> {
            return cartItem.getCount();
        }).reduce((Integer preValue,Integer nextValue) -> {
           return Integer.sum(preValue,nextValue);
        });*/

        return cartItemMap.
                values().  //取map里边所有的值
                stream().  //得到一个Stream对象
                map(cartItem -> cartItem.getCount()).   //希望把每个cartItem中的count取出来，所以调用map方法做一个映射，取出每一个CartItem的count值
                reduce(Integer::sum).  //调用reduce进行汇总 ，返回一个Optional类型
                get();  //如果想要Optional中的值本身 ，就需要调用get方法
    }

    /**
     * 计算 并返回购物车中的购物车中商品的总金额
     */
    public Double getTotalAmount(){
        if (cartItemMap == null || cartItemMap.size() == 0){
            return 0d;
        }
        return cartItemMap.values().stream().map(cartItem -> cartItem.getAmount()).reduce(Double :: sum).get();
    }

    /**
     * 返回所有CartItem 组成的List集合
     */
    public List<CartItem> getCartItemList(){
        return cartItemMap.values().stream().collect(Collectors.toList());
    }

    /**
     * 删除购物项cartItem
     */
    public void removeCartItem(Integer bookId){
        cartItemMap.remove(bookId);
    }

    /**
     * 修改购物项的数量
     */
    public void updateCartItemCount(Integer bookId,Integer count){
        //1.
        CartItem cartItem = cartItemMap.get(bookId);

        if (count == 0){
            cartItemMap.remove(bookId);
        } else {
            cartItem.setCount(count);
        }
    }

    /**
     * 清理购物车
     */
    public void clearCart() {
        cartItemMap.clear();
    }

}
