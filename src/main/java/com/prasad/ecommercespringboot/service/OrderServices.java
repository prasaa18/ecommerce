package com.prasad.ecommercespringboot.service;

import com.prasad.ecommercespringboot.Exception.OrderException;
import com.prasad.ecommercespringboot.model.Address;
import com.prasad.ecommercespringboot.model.Order;
import com.prasad.ecommercespringboot.model.User;

import java.util.List;

public interface OrderServices {

    public Order createOrder(User user, Address shippingAdress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId)throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public List<Order>getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;

}
