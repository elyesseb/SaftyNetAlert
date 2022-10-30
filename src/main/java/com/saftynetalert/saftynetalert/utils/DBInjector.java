package com.saftynetalert.saftynetalert.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saftynetalert.saftynetalert.dto.AddressDto;
import com.saftynetalert.saftynetalert.dto.FirestationDto;
import com.saftynetalert.saftynetalert.dto.StationDto;
import com.saftynetalert.saftynetalert.dto.UserDto;
import com.saftynetalert.saftynetalert.enums.Role;
import com.saftynetalert.saftynetalert.services.AddressService;
import com.saftynetalert.saftynetalert.services.FirestationService;
import com.saftynetalert.saftynetalert.services.StationService;
import com.saftynetalert.saftynetalert.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Component
public class DBInjector{

    @Bean
    CommandLineRunner injector(AddressService addressService, UserService userService, StationService stationService, FirestationService firestationService){
        return args ->{
            DBModel dbModel = DeserializeJsonData();

            int addressInjected = 0;
            for (AddressDto address: dbModel.address) {
                try {
                    addressService.AddAddress(address);
                    addressInjected++;
                    System.out.println("Address '"+ address.getAddress() +"' Inject Success !");
                }catch(Exception e){
                    System.out.println("Address '"+ address.getAddress() +"' Inject Fail !\nError ->" + e.getMessage());
                }
            }

            int usersInjected = 0;
            for (UserDto user: dbModel.users) {
                try{
                    userService.AddUser(user , Role.CITIZEN);
                    usersInjected++;
                    System.out.println("user '"+ user.getEmail() +"' Inject Success !");
                }catch(Exception e){
                    System.out.println("user '"+ user.getEmail() +"' Inject Fail !\nError ->" + e.getMessage());
                }
            }

            int stationInjected = 0;
            for (StationDto station: dbModel.stations) {
                try{
                    stationService.add(station);
                    stationInjected++;
                    System.out.println("Station '"+ station.getName() +"' Inject Success !");
                }catch(Exception e){
                    System.out.println("Station '"+ station.getName() +"' Inject Fail !\nError ->" + e.getMessage());
                }
            }

            int firestationInjected = 0;
            for (FirestationDto firestation: dbModel.firestations) {
                try{
                    firestationService.add(firestation);
                    firestationInjected++;
                    System.out.println("Firestation '"+ firestation.getAddress().getAddress() +"' Inject Success !");
                }catch(Exception e){
                    System.out.println("Firestation '"+ firestation.getAddress().getAddress() +"' Inject Fail !\nError ->" + e.getMessage());
                }
            }

            System.out.println("\n_________________");
            System.out.println("Address ["+addressInjected + "/" + Arrays.stream(dbModel.address).count() + "]");
            System.out.println("Users ["+usersInjected + "/" + Arrays.stream(dbModel.users).count() + "]");
            System.out.println("Stations ["+stationInjected + "/" + Arrays.stream(dbModel.stations).count() + "]");
            System.out.println("Firestation ["+firestationInjected + "/" + Arrays.stream(dbModel.firestations).count() + "]");
        };
    }

    private static DBModel DeserializeJsonData()
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<DBModel> typeReference = new TypeReference<DBModel>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/JsonDataParser/json/data.json");

        try
        {
            return new ObjectMapper().readValue(inputStream, typeReference);
        }
        catch (IOException e)
        { e.printStackTrace();
            return null;
        }
    }
}
