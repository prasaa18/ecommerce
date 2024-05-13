package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.model.OrderItem;
import com.prasad.ecommercespringboot.repository.OrderItemRepository;
import com.prasad.ecommercespringboot.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository=orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
