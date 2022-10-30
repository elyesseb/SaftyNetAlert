package com.saftynetalert.saftynetalert.dto;

import com.saftynetalert.saftynetalert.entities.MedicalRecord;

public class UserForFireDto {
    private String firstname;

    private String lastname;

    private int age;

    private Long firestationInCharge;

    private MedicalRecord medicalRecord;

    public UserForFireDto() {
    }

    public UserForFireDto(String firstname, String lastname, int age, Long firestationInCharge, MedicalRecord medicalRecord) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.firestationInCharge = firestationInCharge;
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

    public Long getFirestationInCharge() {
        return firestationInCharge;
    }

    public void setFirestationInCharge(Long firestationInCharge) {
        this.firestationInCharge = firestationInCharge;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
