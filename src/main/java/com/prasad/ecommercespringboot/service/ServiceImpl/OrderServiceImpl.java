package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.OrderException;
import com.prasad.ecommercespringboot.model.*;
import com.prasad.ecommercespringboot.repository.*;
import com.prasad.ecommercespringboot.service.CartService;
import com.prasad.ecommercespringboot.service.OrderItemService;
import com.prasad.ecommercespringboot.service.OrderServices;
import com.prasad.ecommercespringboot.userdomain.OrderStatus;
import com.prasad.ecommercespringboot.userdomain.PaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderServices {



    private  OrderRepository orderRepository;
    private CartService cartService;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;




    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService,
                            AddressRepository addressRepository, UserRepository userRepository,
                            OrderItemService orderItemService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderItemService = orderItemService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order createOrder(User user, Address shippingAdress) {

        shippingAdress.setUser(user);
        Address address= addressRepository.save(shippingAdress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart=cartService.findUserCart(user.getId());
        List<OrderItem> orderItems=new ArrayList<>();

        for(CartItem item: cart.getCartItems()) {
            OrderItem orderItem=new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());


            OrderItem createdOrderItem=orderItemRepository.save(orderItem);

            orderItems.add(createdOrderItem);
        }


        Order createdOrder=new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscounte(cart.getDiscounte());
        createdOrder.setTotalItem(cart.getTotalItem());

        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus(String.valueOf(OrderStatus.PENDING));
        createdOrder.getPaymentDetails().setStatus(String.valueOf(PaymentStatus.PENDING));
        createdOrder.setCreateAt(LocalDateTime.now());

        Order savedOrder=orderRepository.save(createdOrder);

        for(OrderItem item:orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;

    }



    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(String.valueOf(OrderStatus.PLACED));
        order.getPaymentDetails().setStatus(String.valueOf(PaymentStatus.COMPLETED));
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(String.valueOf(OrderStatus.CONFIRMED));


        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(String.valueOf(OrderStatus.SHIPPED));
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(String.valueOf(OrderStatus.DELIVERED));
        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(String.valueOf(OrderStatus.CANCELLED));
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt=orderRepository.findById(orderId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new OrderException("order not exist with id "+orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> orders=orderRepository.getUsersOrders(userId);
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAllByOrderByCreateAtDesc();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);

        orderRepository.deleteById(orderId);

    }

}


