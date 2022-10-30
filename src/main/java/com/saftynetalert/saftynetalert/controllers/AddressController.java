package com.saftynetalert.saftynetalert.controllers;

import com.saftynetalert.saftynetalert.dto.AddressDto;
import com.saftynetalert.saftynetalert.entities.Address;
import com.saftynetalert.saftynetalert.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public Address Add(@RequestBody AddressDto addressDto){
        return addressService.AddAddress(addressDto);
    }

    @GetMapping("/all")
    public List<AddressDto> GetAll() { return addressService.GetAllAddresses(); }

    @GetMapping("")
    public List<Address> retrieveAll() {
        return addressService.retrieveAll();
    }
}
