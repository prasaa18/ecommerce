package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.AddItemRequest;
import com.prasad.ecommercespringboot.model.Cart;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.CartRepository;
import com.prasad.ecommercespringboot.service.CartItemService;
import com.prasad.ecommercespringboot.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private CartItemService cartService;

    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }


    4.26
}
