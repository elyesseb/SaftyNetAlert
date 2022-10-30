package com.saftynetalert.saftynetalert.services;

import com.saftynetalert.saftynetalert.dto.StationDto;
import com.saftynetalert.saftynetalert.dto.UserDto;
import com.saftynetalert.saftynetalert.entities.Station;
import com.saftynetalert.saftynetalert.entities.User;
import com.saftynetalert.saftynetalert.repositories.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;

    public Station add(StationDto stationDto){
        boolean isStationExist = stationRepository.findByName(stationDto.getName()).isPresent();
        if(!isStationExist){
            Station station = new Station();
            station.setName(stationDto.getName());

            stationRepository.save(station);
            return station;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "station_already_exist");
        }
    }

    public boolean delete(StationDto stationDto){
        Station station = stationRepository.findByName(stationDto.getName()).get();
        if(station != null){
            stationRepository.delete(station);
            return true;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "station_not_exist");
        }
    }

    public List<Station> retrieveAll() {
        return stationRepository.findAll();
    }
}
