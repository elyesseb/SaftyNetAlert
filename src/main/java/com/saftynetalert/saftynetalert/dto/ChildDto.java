package com.saftynetalert.saftynetalert.dto;

import com.saftynetalert.saftynetalert.entities.Address;
import com.saftynetalert.saftynetalert.entities.AddressId;
import com.saftynetalert.saftynetalert.entitiesDto.UserEntityDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChildDto {

    private String firstname;

    private String lastname;

    private int age;


    public ChildDto() {
    }

    public ChildDto(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
