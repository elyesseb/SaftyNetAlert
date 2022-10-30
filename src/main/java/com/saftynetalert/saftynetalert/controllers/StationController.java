package com.saftynetalert.saftynetalert.controllers;


import com.saftynetalert.saftynetalert.dto.StationDto;
import com.saftynetalert.saftynetalert.dto.UserDto;
import com.saftynetalert.saftynetalert.entities.Station;
import com.saftynetalert.saftynetalert.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("")
    public List<Station> retrieveAll() {
        return stationService.retrieveAll();
    }

    @PostMapping("/add")
    public Station addStation(@RequestBody StationDto stationDto){
        return stationService.add(stationDto);
    }

    @DeleteMapping("/delete")
    public boolean removeStation(@RequestBody StationDto stationDto){
        return stationService.delete(stationDto);
    }
}
