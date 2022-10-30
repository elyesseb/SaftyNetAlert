package com.saftynetalert.saftynetalert.dto;

import com.saftynetalert.saftynetalert.entities.AddressId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private String address;
    private Long zip;
    private String city;
    private String state;

    public AddressId toAddressId(){
        return new AddressId(address,  zip , city , state);
    }
    public void FromAddressId(AddressId addressId){
        address = addressId.getAddress();
        zip = addressId.getZip();
        city = addressId.getCity();
        state = addressId.getState();
    }
}
