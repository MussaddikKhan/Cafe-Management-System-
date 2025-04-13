package com.cafemate.Cafe.Managment.System.controller;

import com.cafemate.Cafe.Managment.System.dto.CartItemsDTO;
import com.cafemate.Cafe.Managment.System.model.Cart;
import com.cafemate.Cafe.Managment.System.model.CartItems;
import com.cafemate.Cafe.Managment.System.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public void add(@RequestBody CartItemsDTO cartItemsDTO){
         cartService.add(cartItemsDTO.getCartItemId(), cartItemsDTO.getUserId());
    }
    @DeleteMapping("/remove")
    public  void remove(@RequestBody CartItemsDTO cartItemsDTO){
        cartService.remove(cartItemsDTO.getCartItemId(), cartItemsDTO.getUserId());
    }
    @GetMapping("/get/cart")
    public Cart getItems(@RequestParam Long userId) {
        //get cart instead of cartItems and ignore user 
        return cartService.getCart(userId);
    }
}
