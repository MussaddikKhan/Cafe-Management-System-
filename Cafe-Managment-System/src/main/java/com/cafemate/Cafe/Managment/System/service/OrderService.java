package com.cafemate.Cafe.Managment.System.service;

import com.cafemate.Cafe.Managment.System.model.OrderItems;
import com.cafemate.Cafe.Managment.System.model.OrderStatus;
import com.cafemate.Cafe.Managment.System.model.Orders;
import com.cafemate.Cafe.Managment.System.model.User;
import com.cafemate.Cafe.Managment.System.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    public String makeOrder(Long id) {
        // Step 1: Fetch User
        User user = userService.getUser(id);

        // Step 2: Convert CartItems to OrderItems and associate with the Order
        Set<OrderItems> orderItemsSet = user.getCart().getCartItems().stream()
                .map(ci -> OrderItems.builder()
                        .item(ci.getItem())
                        .priceAtOrder(ci.getTotalPrice())
                        .quantity(ci.getQuantity())
                        .orders(null) // Set the order reference to null (it will be set when saving)
                        .build())
                .collect(Collectors.toSet());

        // Step 3: Create Order and set properties
        Orders orders = new Orders();
        orders.setOrderItems(orderItemsSet);
        orders.setTotalPrice(user.getCart().getTotalPrice());
        orders.setOrderStatus(OrderStatus.PLACED);  // Set initial order status

        // Step 4: Associate order with user (if needed)
        user.getOrders().add(orders);   // Optional: You can add this order to the user's orders list

        // Step 5: Save Order to DB (cascade will save order items automatically)
        orderRepository.save(orders);
        
        // Step 7: Save user
        userService.create(user);

        return "Order Placed Successfully!";
    }

    public String updateOrder(Long orderId, Long userId, OrderStatus orderStatus) {

        // Step 1 : Fetch User
        User user = userService.getUser(userId);

        // Step 2 : Fetch Order from user
        Orders orders = user.getOrders().stream().filter(o -> o.getId().equals(orderId)).findFirst().orElse(null);
        if (orders.getOrderStatus() == OrderStatus.DELIVERED && orderStatus == OrderStatus.CANCELLED) {
            return "You can't cancel a delivered order!";
        }

        if (orders.getOrderStatus() == OrderStatus.CANCELLED) {
            return "Order is already cancelled!";
        }

        orders.setOrderStatus(orderStatus);
        userService.create(user);
        return "Order status updated to " + orderStatus + "!";
    }
}
