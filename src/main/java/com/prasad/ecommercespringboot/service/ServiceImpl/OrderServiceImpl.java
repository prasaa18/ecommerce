package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.OrderException;
import com.prasad.ecommercespringboot.model.Address;
import com.prasad.ecommercespringboot.model.Order;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.service.CartService;
import com.prasad.ecommercespringboot.repository.CartRepository;
import com.prasad.ecommercespringboot.service.OrderServices;
import com.prasad.ecommercespringboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderServices {


    private CartRepository cartRepository;
    private CartService cartservice;
    private ProductService productService;

    public OrderServiceImpl(CartRepository cartRepository,
                            CartService cartItemservice,
                            ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartservice = cartItemservice;
        this.productService = productService;
    }

    @Override
    public Order createOrder(User user, Address shippingAdress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
