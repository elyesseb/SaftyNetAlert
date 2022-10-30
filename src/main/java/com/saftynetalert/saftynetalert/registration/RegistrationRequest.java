package com.saftynetalert.saftynetalert.registration;

import com.saftynetalert.saftynetalert.dto.AddressDto;
import com.saftynetalert.saftynetalert.dto.MedicalRecordDto;
import com.saftynetalert.saftynetalert.entities.Address;
import com.saftynetalert.saftynetalert.entities.AddressId;
import com.saftynetalert.saftynetalert.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    private final String firstname;
    private final String lastname;
    private final Date birthDate;
    private final String phone;
    private final String email;
    private final String password;
    private final AddressDto address;
    private final MedicalRecordDto medicalRecord;

}
