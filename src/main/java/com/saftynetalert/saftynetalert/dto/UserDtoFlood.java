package com.saftynetalert.saftynetalert.dto;

import com.saftynetalert.saftynetalert.entities.MedicalRecord;

public class UserDtoFlood {

    private String firstname;

    private String lastname;

    private String phone;

    private MedicalRecord medicalRecord;

    public UserDtoFlood() {
    }

    public UserDtoFlood(String firstname, String lastname, String phone, MedicalRecord medicalRecord) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
