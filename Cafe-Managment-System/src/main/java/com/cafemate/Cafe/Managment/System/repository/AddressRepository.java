package com.cafemate.Cafe.Managment.System.repository;

import com.cafemate.Cafe.Managment.System.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
