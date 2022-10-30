package com.saftynetalert.saftynetalert.controllers;

import com.saftynetalert.saftynetalert.dto.*;
import com.saftynetalert.saftynetalert.entitiesDto.UserEntityDto;
import com.saftynetalert.saftynetalert.enums.Role;
import com.saftynetalert.saftynetalert.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserEntityDto> retrieveAll() {
        return userService.retrieveAll();
    }

    @PutMapping("/edit/{personId}")
    public UserEntityDto updateUser(@PathVariable Long personId, @RequestBody UserEntityDto userEntityDto) {
        return userService.updateUser(personId, userEntityDto);
    }

    @GetMapping("/{personId}")
    public UserEntityDto findByPersonId(@PathVariable Long personId) {
        return userService.findByPersonId(personId);
    }

    @PostMapping("/citizen/add")
    public RegistrationSuccessDto addCitizenUser(@RequestBody UserDto userDto){
        return userService.AddUser(userDto, Role.CITIZEN);
    }

    @PostMapping("/medical/add")
    public RegistrationSuccessDto addMedicalAssistUser(@RequestBody UserDto userDto){
        return userService.AddUser(userDto, Role.MEDICAL_ASSIST);
    }

    @PostMapping("/moderator/add")
    public RegistrationSuccessDto addModeratorUser(@RequestBody UserDto userDto){
        return userService.AddUser(userDto, Role.MODERATOR);
    }

    @GetMapping("/childAlert")
    public Map<String, List> childAtAddress(@RequestParam() String address) {
        return userService.childAtAddress(address);
    }

    @GetMapping("/communityEmail")
    public List<String> findEmailByCity(@RequestParam String city) {
        return userService.findEmailByCity(city);
    }

    @GetMapping("/personInfo")
    public List<UserInfoDto> findUsersByFirstAndOrLastName(
            @RequestParam String firstname,
            @RequestParam String lastname
    ) {
        return userService.findUsersByFirstAndOrLastName(firstname, lastname);
    }

    @GetMapping("/getPerson")
    public UserEntityDto getUserByMail(@RequestParam() String mail) {
        return userService.findUserByMail(mail);
    }

    @GetMapping("/fire")
    public List<UserForFireDto> getUserByAddress(@RequestParam() String address) {
        return userService.getUserByAddress(address);
    }

    @GetMapping("/flood/station")
    public List<Map> getByStationAndSortByAddress(@RequestParam() Long stationId) {
        return userService.getByStationAndSortByAddress(stationId);
    }

    @DeleteMapping("/{personId}")
    public void delete(@PathVariable Long personId) {
        userService.delete(personId);
    }

}
