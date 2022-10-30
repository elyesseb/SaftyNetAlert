package com.saftynetalert.saftynetalert.repositories;

import com.saftynetalert.saftynetalert.dto.FirestationDto;
import com.saftynetalert.saftynetalert.dto.UserInfoForFirestationDto;
import com.saftynetalert.saftynetalert.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FirestationRepository extends JpaRepository<Firestation, Long> {

    Optional<Firestation> findByStation(Station station);
    Optional<Firestation> findByAddress(Address address);

    List<Firestation> findAllByAddress_AddressIdAndStation_Name(AddressId addressId, String station_Name);

    Optional<Firestation> findByStationName(String name);

    List<Firestation> findAllByAddress(Address address);
    List<Firestation> findAllByStation(Station station);
    Optional<Firestation> findAllByAddress_AddressId_Address(String address);

    List<Firestation> findAllByStation_Id(Long stationId);

//    @Query(value = "SELECT u.first_name as firstname, u.last_name as lastname, u.phone as phone, u.address_address as address, u.address_city as city, u.address_state as state, u.address_zip as zip FROM users u\n" +
//            "INNER JOIN firestation f\n" +
//            "ON u.address_address = f.address_address\n" +
//            "WHERE f.station_id = ?1", nativeQuery = true)
//    Object[] findAllUsersByStationId(Long stationId);


    // SELECT u.first_name as firstname, u.last_name as lastname, u.phone as phone, u.address_address as address FROM users u INNER JOIN firestation f ON u.address_address = f.address_address WHERE f.station_id = 1

}
