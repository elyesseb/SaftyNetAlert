package com.saftynetalert.saftynetalert.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "medications", nullable = true)
    @ElementCollection
    private List<String> medications = new ArrayList<String>();

    @Column(name = "allergies", nullable = true)
    @ElementCollection
    private List<String> allergies = new ArrayList<String>();

}
