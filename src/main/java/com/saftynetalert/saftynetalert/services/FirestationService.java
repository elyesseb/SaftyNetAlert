package com.saftynetalert.saftynetalert.services;

import com.saftynetalert.saftynetalert.config.Mapper;
import com.saftynetalert.saftynetalert.dto.FirestationDto;
import com.saftynetalert.saftynetalert.dto.UserInfoForFirestationDto;
import com.saftynetalert.saftynetalert.entities.Address;
import com.saftynetalert.saftynetalert.entities.Firestation;
import com.saftynetalert.saftynetalert.entities.Station;
import com.saftynetalert.saftynetalert.entities.User;
import com.saftynetalert.saftynetalert.repositories.AddressRepository;
import com.saftynetalert.saftynetalert.repositories.FirestationRepository;
import com.saftynetalert.saftynetalert.repositories.StationRepository;
import com.saftynetalert.saftynetalert.repositories.UserRepository;
import com.saftynetalert.saftynetalert.utilities.MapperHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@AllArgsConstructor
@Service
public class FirestationService {

    private final FirestationRepository firestationRepository;
    private final StationRepository stationRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Firestation add(FirestationDto firestationDto) {
        Station station = stationRepository.findByName(firestationDto.getStation().getName()).get();
        Address address = addressRepository.findByAddressId(firestationDto.getAddress().toAddressId()).get();

        List<Firestation> allFirestations =
                firestationRepository.findAllByAddress_AddressIdAndStation_Name(
                        firestationDto.getAddress().toAddressId(),
                        firestationDto.getStation().getName()
                );

        boolean isFirestationExist =  allFirestations.size() > 0;


        if (!isFirestationExist)
        {
            Firestation firestation = new Firestation();
            firestation.setStation(station);
            firestation.setAddress(address);

            firestationRepository.save(firestation);
            return firestation;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "firestation_already_exist");
        }
    }

    public boolean remove(Firestation firestation) {
        if (firestationRepository.findById(firestation.getId()).isPresent()) {
            firestationRepository.delete(firestation);
            return true;
        }

        return false;
    }

    public Map<String, List<Map>> findPersonsByFirestationNumber(Long stationNumber) {
        List<Firestation> firestationList = firestationRepository.findAllByStation_Id(stationNumber);
        List<User> userList = userRepository.findAll();
        List<UserInfoForFirestationDto> listResult = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        int majeur = 0;
        int mineur = 0;

        for (var user: userList) {
            for (var firestation: firestationList) {
                if (user.getAddress().equals(firestation.getAddress())) {
                    var userInfo = mapper.map(user, UserInfoForFirestationDto.class);
                    listResult.add(userInfo);

                    if (user.getAge() > 18) {
                        majeur++;
                    } else {
                        mineur++;
                    }
                }
            }
        }
        Map<String, Integer> mapAge = new HashMap<>();
        mapAge.put("Child", mineur);
        mapAge.put("Adult", majeur);
        Map<String, List<UserInfoForFirestationDto>> mapUser = new HashMap<>();
        mapUser.put("CoveredPerson", listResult);
        Map<String, List<Map>> mapFinal = new HashMap<>();
        List<Map>mapList = new ArrayList<>();
        mapList.add(mapUser);
        mapList.add(mapAge);
        mapFinal.put("Persons", mapList);

        return mapFinal;
    }

    public List<String> findUsersPhoneByFirestationNumber(Long firestation) {
        Optional<Firestation> firestationOptional = firestationRepository.findById(firestation);
        List<User> userList = userRepository.findAll();
        List<String> phoneList = new ArrayList<String>();
        if (firestationOptional.isPresent()) {
            for (var user:userList) {
                if ((user.getAddress().getAddressId().getAddress()).equals(firestationOptional.get().getAddress().getAddressId().getAddress())) {
                    phoneList.add(user.getPhone());
                }
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "firestation with id" + firestation + "does not exist");
        }
        return phoneList;
    }

    public List<Firestation> findAll() {
        return firestationRepository.findAll();
    }

    public Firestation updateFirestation(Long firestationId, Firestation firestation) {
        Optional<Firestation> firestationOptional = firestationRepository.findById(firestationId);
        if (firestationOptional.isPresent()) {
            firestationOptional.get().setStation(firestation.getStation());
            firestationOptional.get().setAddress(firestation.getAddress());
            firestationRepository.save(firestationOptional.get());
        }
        return firestationOptional.get();
    }

    public void deleteFirestation(Long firestationId) {
        Optional<Firestation> firestation = firestationRepository.findById(firestationId);
        if (firestation.isPresent()) {
            firestationRepository.deleteById(firestationId);
        }
    }

    public Firestation getFirestationById(Long firestationId) {
        Optional<Firestation> firestation = firestationRepository.findById(firestationId);
        return firestation.get();
    }

//    public Firestation save(Firestation firestation) {
//        List<Firestation> firestationList = firestationRepository.findAll();
//        for (var f:firestationList) {
//            if (firestation.getAddress().equals(firestation.getAddress())) {
//                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "firestation_already_exist");
//            }
//            else{
//                firestationRepository.save(firestation);
//            }
//        }
//        return firestation;
//    }
}
