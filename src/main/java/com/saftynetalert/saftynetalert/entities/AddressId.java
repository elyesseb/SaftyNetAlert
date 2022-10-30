package com.saftynetalert.saftynetalert.entities;

import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressId implements Serializable
{
    private String address;
    private Long zip;
    private String city;
    private String state;
}
