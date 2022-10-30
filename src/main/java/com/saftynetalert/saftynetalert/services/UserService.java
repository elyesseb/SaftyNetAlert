package com.saftynetalert.saftynetalert.services;

import com.saftynetalert.saftynetalert.dto.*;
import com.saftynetalert.saftynetalert.entities.*;
import com.saftynetalert.saftynetalert.entitiesDto.UserEntityDto;
import com.saftynetalert.saftynetalert.enums.Role;
import com.saftynetalert.saftynetalert.repositories.AddressRepository;
import com.saftynetalert.saftynetalert.repositories.FirestationRepository;
import com.saftynetalert.saftynetalert.repositories.MedicalRecordRepository;
import com.saftynetalert.saftynetalert.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
    private final String NOT_FOUND_EMAIL_EXCEPTION = "User with %s email not found";
    private final String EXIST_EMAIL_EXCEPTION = "%s email already exist";
    private final String EXIST_PHONE_EXCEPTION = "%s phone already exist";

    private final UserRepository userRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final AddressRepository addressRepository;
    private final FirestationRepository firestationRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(String.format(NOT_FOUND_EMAIL_EXCEPTION, email))
        );
    }

    public RegistrationSuccessDto AddUser(UserDto userDto, Role role){
        AddressId addressId = userDto.getAddress().toAddressId();

        return addressRepository.findByAddressId(addressId).map(address -> {

            if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "email_already_exist");
            }

            User user = new User();
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setDescription(userDto.getMedicalRecord().getDescription());
            medicalRecord.setMedications(Arrays.stream(userDto.getMedicalRecord().getMedications()).collect(Collectors.toList()));
            medicalRecord.setAllergies(Arrays.stream(userDto.getMedicalRecord().getAllergies()).collect(Collectors.toList()));

            String encryptedPwd = bCryptPasswordEncoder.encode(userDto.getPassword());

            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setBirthdate(userDto.getBirthdate());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());
            user.setPassword(encryptedPwd);
            user.setMedicalRecord(medicalRecord);
            user.setAddress(address);
            user.setRole(role);

            medicalRecordRepository.save(medicalRecord);
            userRepository.save(user);

            String token = UUID.randomUUID().toString();

            // TODO : SEND EMAIL

            //return token;
            return new RegistrationSuccessDto(token);
        }).orElseThrow(
            () -> {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "address_not_found");
            }
        );
    }

    public String signUpUser(User user) {
        boolean isEmailExist = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean isPhoneExist = userRepository.findByPhone(user.getUsername()).isPresent();

        if(isEmailExist){
            throw new IllegalStateException(String.format(EXIST_EMAIL_EXCEPTION, user.getEmail()));
        }
        if(isPhoneExist)
        {
            throw new IllegalStateException(String.format(EXIST_PHONE_EXCEPTION, user.getPhone()));
        }
        String encryptedPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        // TODO : SEND EMAIL

        return token;
    }

    public boolean DeleteByEmail(String email){
        var user = userRepository.findByEmail(email);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }

        return false;
    }

    public List<String> findEmailByCity(String city) {
        List<User> allUser = userRepository.findAll();
        List<String> mailList = new ArrayList<String>();
        for (var user:allUser) {
            if (user.getAddress().getAddressId().getCity().equalsIgnoreCase(city)) {
                mailList.add(user.getEmail());
            }
//            else {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "city" + city + "does not exist");
//            }
        }
        return mailList;
    }

    public List<UserInfoDto> findUsersByFirstAndOrLastName(String firstname, String lastname) {
        List<User> allUser = userRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        List<UserInfoDto> userList = new ArrayList<>();

        for (var user:allUser) {
            UserInfoDto userInfoDto = mapper.map(user, UserInfoDto.class);
            if (user.getFirstname().equalsIgnoreCase(firstname) && user.getLastname().equalsIgnoreCase(lastname)) {
                userInfoDto.setAddress(user.getAddress().getAddressId());
                userInfoDto.setAge(user.getAge());
                userList.add(userInfoDto);
            }
        }
        if (userList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "name " + firstname + " or " + lastname + " does not exist");
        }
        return userList;
    }

    public UserEntityDto findUserByMail(String mail) {
        Optional<User> user = userRepository.findByEmail(mail);
        ModelMapper mapper = new ModelMapper();
        UserEntityDto userEntityDto = mapper.map(user, UserEntityDto.class);
        return userEntityDto;
    }

    public List<UserEntityDto> retrieveAll() {
        List<User> userList = userRepository.findAll();
        List<UserEntityDto> userEntityDtoList = new ArrayList<UserEntityDto>();
        ModelMapper mapper = new ModelMapper();
        for (var user:userList) {
            UserEntityDto userEntityDto = mapper.map(user, UserEntityDto.class);
            userEntityDto.setAddress(user.getAddress().getAddressId());
            userEntityDtoList.add(userEntityDto);
        }
        return userEntityDtoList;
    }


    public Map<String, List> childAtAddress(String address) {
        List<User> userList = userRepository.findAll();
        List<ChildDto> childDtoList = new ArrayList<>();
        List<ChildDto> familyDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        Map<String, List> result = new HashMap<>();
        boolean hasChild = false;

        for (var user:userList) {
            int age = user.getAge();
            boolean addressFound = user.getAddress().getAddressId().getAddress().equalsIgnoreCase(address);

            if (addressFound && age <= 18){
                ChildDto childDto = mapper.map(user, ChildDto.class);
                childDto.setAge(age);
                childDtoList.add(childDto);
                hasChild = true;
            }

            if (addressFound && age > 18) {
                ChildDto familyDto = mapper.map(user, ChildDto.class);
                familyDto.setAge(age);
                familyDtoList.add(familyDto);
            }

        }
        result.put("Child", childDtoList);
        if (hasChild) {
            result.put("Adult", familyDtoList);
        }
        return result;
    }

    public List<UserForFireDto> getUserByAddress(String address) {
        ModelMapper mapper = new ModelMapper();
        List<UserForFireDto> listFinal = new ArrayList<>();
        List<User> userList = userRepository.findAllByAddress_AddressId_Address(address);
        Optional<Firestation> firestation = firestationRepository.findAllByAddress_AddressId_Address(address);

        for (var user:userList) {
            UserForFireDto userForFireDto = mapper.map(user, UserForFireDto.class);
            userForFireDto.setFirestationInCharge(firestation.get().getStation().getId());
            listFinal.add(userForFireDto);
        }
        return listFinal;
    }

    public List<Map> getByStationAndSortByAddress(Long stationId) {
        ModelMapper mapper = new ModelMapper();
        List<Firestation> firestationList = firestationRepository.findAllByStation_Id(stationId);
        List<User> userList = userRepository.findAll();
        List<Map> mapList = new ArrayList<>();

        for (var firestation: firestationList) {
            Map<String, String> addressMap = new HashMap<>();
            addressMap.put("address", firestation.getAddress().getAddressId().getAddress());
            addressMap.put("city", firestation.getAddress().getAddressId().getCity());
            addressMap.put("state", firestation.getAddress().getAddressId().getState());
            addressMap.put("zip", firestation.getAddress().getAddressId().getZip().toString());
            List<UserDtoFlood> userDtoFloodList = new ArrayList<>();
            Map<Map<String, String>, List<UserDtoFlood>> map = new HashMap<>();
            for (var user:userList) {
                if (firestation.getAddress().equals(user.getAddress())) {
                    UserDtoFlood userDtoFlood = mapper.map(user, UserDtoFlood.class);
                    userDtoFloodList.add(userDtoFlood);
                }
            }
            map.put(addressMap, userDtoFloodList);
            mapList.add(map);
        }

        return mapList;
    }

    public UserEntityDto updateUser(Long personId, UserEntityDto userEntityDto) {
        Optional<User> user = userRepository.findById(personId);
        ModelMapper mapper = new ModelMapper();
//        userEntityDto = mapper.map(user.get(), UserEntityDto.class);
//        userRepository.save(mapper.map(userEntityDto, User.class));
        if (user.isPresent()) {
            user.get().setFirstname(userEntityDto.getFirstname());
            user.get().setLastname(userEntityDto.getLastname());
            user.get().setBirthdate(userEntityDto.getBirthdate());
            user.get().setPhone(userEntityDto.getPhone());
            user.get().setEmail(userEntityDto.getEmail());
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setDescription(userEntityDto.getMedicalRecord().getDescription());
            medicalRecord.setMedications(userEntityDto.getMedicalRecord().getMedications());
            medicalRecord.setAllergies(userEntityDto.getMedicalRecord().getAllergies());
            user.get().setMedicalRecord(medicalRecord);
            medicalRecordRepository.save(medicalRecord);
            Address address = new Address();
            address.setAddressId(userEntityDto.getAddress());
            user.get().setAddress(address);
            addressRepository.save(address);
            user.get().setRole(userEntityDto.getRole());
            userEntityDto = mapper.map(user.get(), UserEntityDto.class);
            userRepository.save(user.get());

        }
        return userEntityDto;
    }

    public UserEntityDto findByPersonId(Long personId) {
        Optional<User> user = userRepository.findById(personId);
        ModelMapper mapper = new ModelMapper();
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "user_not_found");
        }
        UserEntityDto userEntityDto = mapper.map(user.get(), UserEntityDto.class);
        return userEntityDto;
    }

    public void delete(Long personId) {
        userRepository.deleteById(personId);
    }
}
