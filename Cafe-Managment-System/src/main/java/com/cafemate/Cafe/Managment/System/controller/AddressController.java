package com.cafemate.Cafe.Managment.System.controller;

import com.cafemate.Cafe.Managment.System.dto.AddressDTO;
import com.cafemate.Cafe.Managment.System.model.Address;
import com.cafemate.Cafe.Managment.System.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public Address add(@RequestBody Address address, @RequestParam Long id){
        return addressService.add(address, id);
    }

    @DeleteMapping("/remove")
    public String  remove(@RequestBody AddressDTO addressDTO){
         return addressService.remove(addressDTO.getAddressId(), addressDTO.getUserId());
    }
    @GetMapping("/get")
    public  Address get(@RequestParam Long addressId){
        return addressService.get(addressId); 
    }
    @GetMapping("/get/all")
    public List<Address> getAll(@RequestParam Long userId){
        return addressService.getAll(userId);
    }

}
