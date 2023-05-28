package com.itwang.book.entity;

import java.math.BigDecimal;

/**
 * 每一个购物项
 */
public class CartItem {
    //外边传进来的
    //购物车实体
    private Book book;
    //外边传进来的
    //购物车数量
    private Integer count;
    //计算得到的，不允许外界输入，所以构造器中不提供入口，也不提供set()方法
    //计算的总金额
    private Double amount;

    public CartItem(Book book, Integer count) {
        this.book = book;
        this.count = count;
    }


//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }

    //仅提供get（）方法 ，允许外界执行读操作，在读操作中执行计算
    public Double getAmount() {

        //1.在计算中，防止大的运算，结果溢出
        //2.bigDecimal更加精确
        BigDecimal price = new BigDecimal(book.getPrice());
        BigDecimal count = new BigDecimal(this.count);
        BigDecimal amount = price.multiply(count);

        this.amount = amount.doubleValue();

        return this.amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
