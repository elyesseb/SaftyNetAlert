package com.saftynetalert.saftynetalert.services;

import com.saftynetalert.saftynetalert.dto.RegistrationSuccessDto;
import com.saftynetalert.saftynetalert.dto.UserDto;
import com.saftynetalert.saftynetalert.entities.Address;
import com.saftynetalert.saftynetalert.entities.MedicalRecord;
import com.saftynetalert.saftynetalert.entities.User;
import com.saftynetalert.saftynetalert.enums.Role;
import com.saftynetalert.saftynetalert.registration.EmailValidator;
import com.saftynetalert.saftynetalert.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class RegistrationService
{
    private final AddressService addressService;
    private final UserService userService;
    private final EmailValidator emailValidator;

    public RegistrationSuccessDto register(@Valid UserDto request) {
        boolean isValidate = emailValidator.test(request.getEmail());
        if(!isValidate)
        {
            throw new IllegalStateException("Email is not valid");
        }

        Address address = addressService.GetAddress(request.getAddress());
        return userService.AddUser(request, Role.CITIZEN);


        /*return userService.signUpUser(new User(
                request.getFirstname(),
                request.getLastname(),
                request.getBirthDate(),
                request.getPhone(),
                request.getEmail(),
                request.getPassword(),
                address,
                Role.MODERATOR
        ));*/
    }
}
