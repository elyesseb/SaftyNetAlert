package com.saftynetalert.saftynetalert.dto;

import com.saftynetalert.saftynetalert.entities.AddressId;
import com.saftynetalert.saftynetalert.entities.MedicalRecord;
import com.saftynetalert.saftynetalert.enums.Role;

import java.sql.Date;

public class UserInfoDto {

    private String firstname;

    private String lastname;

    private int age;

    private String email;

    private AddressId address;

    private MedicalRecord medicalRecord;

    public UserInfoDto() {
    }

    public UserInfoDto(String firstname, String lastname, int age, String email, AddressId address, MedicalRecord medicalRecord) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.address = address;
        this.medicalRecord = medicalRecord;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressId getAddress() {
        return address;
    }

    public void setAddress(AddressId address) {
        this.address = address;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
