package com.saftynetalert.saftynetalert.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserDto {

    private String firstname;
    private String lastname;
    private Date birthdate;
    private String phone;
    private String email;
    private String password;
    private AddressDto address;
    private MedicalRecordDto medicalRecord;

}
