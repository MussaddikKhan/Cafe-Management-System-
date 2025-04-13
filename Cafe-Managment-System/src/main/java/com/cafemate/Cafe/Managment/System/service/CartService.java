package com.cafemate.Cafe.Managment.System.service;

import com.cafemate.Cafe.Managment.System.model.Cart;
import com.cafemate.Cafe.Managment.System.model.CartItems;
import com.cafemate.Cafe.Managment.System.model.Items;
import com.cafemate.Cafe.Managment.System.model.User;
import com.cafemate.Cafe.Managment.System.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemsService itemsService;


    public void createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
    }

    @Transactional
    public void add(Long itemId, Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        Items item = itemsService.getItem(itemId);

        CartItems cartItem = cart.getCartItems().stream()
                .filter(ci -> ci.getItem().getId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItems();
            cartItem.setItem(item);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        updateCartTotalPrice(cart);
        cartRepository.save(cart);
    }

    @Transactional
    public void remove(Long itemId, Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        CartItems cartItem = cart.getCartItems().stream()
                .filter(ci -> ci.getItem().getId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            } else {
                cart.getCartItems().remove(cartItem);
            }
            updateCartTotalPrice(cart);
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Item not found in cart for item id: " + itemId);
        }
    }

    private void updateCartTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(ci -> ci.getItem().getPrice() * ci.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
    }

    public Cart getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return cart;
    }
}
