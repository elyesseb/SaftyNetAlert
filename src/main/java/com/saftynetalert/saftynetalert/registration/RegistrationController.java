package com.saftynetalert.saftynetalert.registration;

import com.saftynetalert.saftynetalert.dto.RegistrationSuccessDto;
import com.saftynetalert.saftynetalert.dto.UserDto;
import com.saftynetalert.saftynetalert.entities.User;
import com.saftynetalert.saftynetalert.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public RegistrationSuccessDto register(@RequestBody UserDto request)
    {
        return registrationService.register(request);
    }
}
