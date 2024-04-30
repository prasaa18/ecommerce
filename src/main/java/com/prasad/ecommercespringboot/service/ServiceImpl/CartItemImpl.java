package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.CartItemException;
import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.model.Cart;
import com.prasad.ecommercespringboot.model.CartItem;
import com.prasad.ecommercespringboot.model.Product;
import com.prasad.ecommercespringboot.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemImpl implements CartItemService {


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return null;
    }

    @Override
    public CartItem updateCartItem(long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        return null;
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return null;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

    }

    @Override
    public CartItem findCArtItemById(Long CartItemId) throws CartItemException {
        return null;
    }
}
