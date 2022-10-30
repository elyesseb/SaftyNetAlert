package com.saftynetalert.saftynetalert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalRecordDto
{
    private String description;
    private String[] medications;
    private String[] allergies;
}
