package com.saftynetalert.saftynetalert.controllers;

import com.saftynetalert.saftynetalert.dto.FirestationDto;
import com.saftynetalert.saftynetalert.dto.UserDto;
import com.saftynetalert.saftynetalert.dto.UserInfoForFirestationDto;
import com.saftynetalert.saftynetalert.entities.Firestation;
import com.saftynetalert.saftynetalert.entities.User;
import com.saftynetalert.saftynetalert.services.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/firestation")
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @PostMapping("/add")
    public Firestation add(@RequestBody FirestationDto firestationDto){
        return firestationService.add(firestationDto);
    }

//    @PostMapping("/add")
//    public Firestation save(@RequestBody Firestation firestation) {
//        return firestationService.save(firestation);
//    }

    @GetMapping("/all")
    public List<Firestation> findAll(){
        return firestationService.findAll();
    }

    @GetMapping("/{firestationId}")
    public Firestation getFirestationById(@PathVariable Long firestationId) {
        return firestationService.getFirestationById(firestationId);
    }

    @PutMapping("/edit/{firestationId}")
    public Firestation updateFirestation(@PathVariable Long firestationId, @RequestBody Firestation firestation) {
        return firestationService.updateFirestation(firestationId, firestation);
    }

    @DeleteMapping("/{firestationId}")
    public void deleteFirestation(@PathVariable Long firestationId) {
        firestationService.deleteFirestation(firestationId);
    }

    @GetMapping("/params")
    public Map<String, List<Map>> findPersonsByFirestationNumber(
            @RequestParam() Long stationNumber) {
        return firestationService.findPersonsByFirestationNumber(stationNumber);
    }

    @GetMapping("/phoneAlerts")
    public List<String> findUsersPhoneByFirestationNumber(@RequestParam Long firestation) {
        return firestationService.findUsersPhoneByFirestationNumber(firestation);
    }
}
