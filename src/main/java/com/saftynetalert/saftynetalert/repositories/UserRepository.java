package com.saftynetalert.saftynetalert.repositories;

import com.saftynetalert.saftynetalert.entities.Address;
import com.saftynetalert.saftynetalert.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAddress(Address address);

    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    List<User> findAllByAddress_AddressId_Address(String address);
}
