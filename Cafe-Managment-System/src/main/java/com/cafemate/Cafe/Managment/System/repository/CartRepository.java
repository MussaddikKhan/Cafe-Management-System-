package com.cafemate.Cafe.Managment.System.repository;

import com.cafemate.Cafe.Managment.System.model.Cart;
import com.cafemate.Cafe.Managment.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart  findByUserId(Long userId);
}
