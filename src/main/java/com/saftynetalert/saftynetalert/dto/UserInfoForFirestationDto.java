package com.saftynetalert.saftynetalert.dto;

import com.saftynetalert.saftynetalert.entities.AddressId;
import com.saftynetalert.saftynetalert.entities.MedicalRecord;
import com.saftynetalert.saftynetalert.enums.Role;

import java.sql.Date;

public class UserInfoForFirestationDto {

    private String firstname;

    private String lastname;

    private String phone;

    private AddressId address;

    public UserInfoForFirestationDto() {
    }

    public UserInfoForFirestationDto(String firstname, String lastname, String phone, AddressId address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressId getAddress() {
        return address;
    }

    public void setAddress(AddressId address) {
        this.address = address;
    }

}
