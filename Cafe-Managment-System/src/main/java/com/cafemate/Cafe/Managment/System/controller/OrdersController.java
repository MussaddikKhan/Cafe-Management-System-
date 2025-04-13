package com.cafemate.Cafe.Managment.System.controller;

import com.cafemate.Cafe.Managment.System.model.OrderStatus;
import com.cafemate.Cafe.Managment.System.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String makeOrder(Long userId){
        return orderService.makeOrder(userId);

    }

    @PutMapping("/update")
    public String updateOrder(Long OrderId, Long userId, OrderStatus orderStatus){
        return orderService.updateOrder(OrderId, userId, orderStatus);
    }
}
