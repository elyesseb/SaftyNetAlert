package com.saftynetalert.saftynetalert.utils;

import com.saftynetalert.saftynetalert.dto.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBModel
{
    public AddressDto[] address;
    public MedicalRecordDto[] medicalRecords;
    public UserDto[] users;
    public StationDto[] stations;
    public FirestationDto[] firestations;
}
