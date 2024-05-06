package com.prasad.ecommercespringboot.controller;


import com.prasad.ecommercespringboot.Exception.OrderException;
import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.model.Address;
import com.prasad.ecommercespringboot.model.Order;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.service.OrderServices;
import com.prasad.ecommercespringboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

        private OrderServices orderService;
        private UserService userService;

        public OrderController(OrderServices orderService,UserService userService) {
            this.orderService=orderService;
            this.userService=userService;
        }

        @PostMapping("/")
        public ResponseEntity<Order> createOrderHandler(@RequestBody Address spippingAddress,
                                                        @RequestHeader("Authorization")String jwt) throws UserException, UserException {

            User user=userService.findUserProfileByJwt(jwt);
            Order order =orderService.createOrder(user, spippingAddress);

            return new ResponseEntity<Order>(order, HttpStatus.OK);

        }

        @GetMapping("/user")
        public ResponseEntity<List<Order>> usersOrderHistoryHandler(@RequestHeader("Authorization")
                                                                     String jwt) throws OrderException, UserException{

            User user=userService.findUserProfileByJwt(jwt);
            List<Order> orders=orderService.usersOrderHistory(user.getId());
            return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
        }

        @GetMapping("/{orderId}")
        public ResponseEntity< Order> findOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")
        String jwt) throws OrderException, UserException{

            User user=userService.findUserProfileByJwt(jwt);
            Order orders=orderService.findOrderById(orderId);
            return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
        }

    }