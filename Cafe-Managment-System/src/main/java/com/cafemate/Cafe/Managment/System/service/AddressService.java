package com.cafemate.Cafe.Managment.System.service;

import com.cafemate.Cafe.Managment.System.model.Address;
import com.cafemate.Cafe.Managment.System.model.User;
import com.cafemate.Cafe.Managment.System.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    public Address add(Address address, Long userId) {
        User user = userService.getUser(userId);
        address.setUser(user);
        return addressRepository.save(address);
    }

    public Address get(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    public String remove(Long addressId, Long userId) {
        User user = userService.getUser(userId);

        Address address = user.getAddress().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElse(null);

        if (address != null) {
            user.getAddress().remove(address);
            userService.create(user);          // Update user (cascade may not delete here)
            return "Address deleted successfully!";
        }
        return "Address not found for this user.";
    }

    public List<Address> getAll(Long userId) {
        User user =  userService.getUser(userId);
        return user.getAddress().stream().toList();
    }
}
