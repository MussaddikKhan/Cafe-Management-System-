package com.cafemate.Cafe.Managment.System.repository;

import com.cafemate.Cafe.Managment.System.model.Category;
import com.cafemate.Cafe.Managment.System.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
    List<Items> findItemsByCategory(Category category);
}
