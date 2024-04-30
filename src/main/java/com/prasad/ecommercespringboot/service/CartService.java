package com.prasad.ecommercespringboot.service;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.AddItemRequest;
import com.prasad.ecommercespringboot.model.Cart;
import com.prasad.ecommercespringboot.model.User;

public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req)throws ProductException;

    public Cart findUserCart(Long userId);


}
