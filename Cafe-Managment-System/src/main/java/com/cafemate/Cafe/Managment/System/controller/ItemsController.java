package com.cafemate.Cafe.Managment.System.controller;

import com.cafemate.Cafe.Managment.System.model.Category;
import com.cafemate.Cafe.Managment.System.model.Items;
import com.cafemate.Cafe.Managment.System.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @PostMapping("/add")
    public Items addItem(@RequestBody Items item){
        return itemsService.add(item) ;
    }
    @PostMapping("/add/all")
    public List<Items> addAllItems(@RequestBody List<Items>items){
        return itemsService.addAll(items) ;
    }
    @GetMapping("/get/{id}")
    public Items getItem(@PathVariable Long id){
        return itemsService.getItem(id);
    }
    @GetMapping("/get/all")
    public  List<Items> getItems(){
        return itemsService.getItems();
    }
    @GetMapping("/get")
    List<Items> getByCategory(@RequestParam Category category){
        return itemsService.getByCategory(category);
    }
}
