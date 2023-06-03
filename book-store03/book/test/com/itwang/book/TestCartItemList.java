package com.itwang.book;

import com.itwang.book.entity.Cart;
import com.itwang.book.entity.CartItem;
import org.junit.Test;

import java.util.List;

public class TestCartItemList {
    @Test
    public void TestGetCartItemList(){
        Cart cart = new Cart();
        List<CartItem> cartItemList = cart.getCartItemList();
        System.out.println("cartItemList = " + cartItemList);
    }
}
