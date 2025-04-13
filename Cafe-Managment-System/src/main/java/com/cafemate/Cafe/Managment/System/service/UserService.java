package com.cafemate.Cafe.Managment.System.service;

import com.cafemate.Cafe.Managment.System.model.User;
import com.cafemate.Cafe.Managment.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  CartService cartService;

    public String create(User user) {
       userRepository.save(user);
       cartService.createCart(user);
       return "User Create Successfully";
    }

    public User getUser(Long id) {
        return  userRepository.findById(id).orElse(null);
    }
}
