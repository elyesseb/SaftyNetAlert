package com.saftynetalert.saftynetalert.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="addresses")
public class Address {

    @EmbeddedId
    private AddressId addressId;

    public Address(AddressId addressId) {
        this.addressId = addressId;
    }

    public Address() {

    }
}
