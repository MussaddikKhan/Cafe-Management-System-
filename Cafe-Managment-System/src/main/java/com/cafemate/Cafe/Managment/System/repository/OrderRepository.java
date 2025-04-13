package com.cafemate.Cafe.Managment.System.repository;

import com.cafemate.Cafe.Managment.System.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
