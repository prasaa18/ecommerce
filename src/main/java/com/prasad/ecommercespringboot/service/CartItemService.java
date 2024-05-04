package com.prasad.ecommercespringboot.service;

import com.prasad.ecommercespringboot.Exception.CartItemException;
import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.model.Cart;
import com.prasad.ecommercespringboot.model.CartItem;
import com.prasad.ecommercespringboot.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(long userId, Long id, CartItem cartItem)throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product , String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId)throws  CartItemException, UserException;

    public  CartItem findCartItemById(Long  CartItemId)throws  CartItemException;
}
