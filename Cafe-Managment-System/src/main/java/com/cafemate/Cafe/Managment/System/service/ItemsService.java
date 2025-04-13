package com.cafemate.Cafe.Managment.System.service;

import com.cafemate.Cafe.Managment.System.model.Category;
import com.cafemate.Cafe.Managment.System.model.Items;
import com.cafemate.Cafe.Managment.System.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    @Autowired
    private ItemRepository itemRepository;

    public Items add(Items item) {
        return itemRepository.save(item);
    }

    public List<Items> addAll(List<Items> items) {
        return itemRepository.saveAll(items);
    }

    public Items getItem(Long id) {
        return itemRepository.findById(id).orElse(null); 
    }

    public List<Items> getItems() {
        return itemRepository.findAll(); 
    }

    public List<Items> getByCategory(Category category) {
        return itemRepository.findItemsByCategory(category);
    }
}
