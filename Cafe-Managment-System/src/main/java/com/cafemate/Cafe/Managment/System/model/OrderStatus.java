package com.cafemate.Cafe.Managment.System.model;

public enum OrderStatus {
    PLACED,           // Order placed successfully
    ACCEPTED,         // Restaurant accepted the order
    COOKING,          // Food is being prepared
    ON_THE_WAY,       // Delivery partner picked up the order
    DELIVERED,        // Order delivered to the customer
    CANCELLED,          // Order cancelled by customer or café
    FAILED              // Order Failed Due to technical reasons 
}
