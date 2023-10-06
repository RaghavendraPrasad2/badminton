package com.advarra.badminton.services.implementations;

import com.advarra.badminton.api.API;
import com.advarra.badminton.dtos.LoginDTO;
import com.advarra.badminton.dtos.RegisterDTO;
import com.advarra.badminton.model.UserCredentials;
import com.advarra.badminton.repositories.RegisterRepository;
import com.advarra.badminton.services.interfaces.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImplementation implements RegisterService {

    @Autowired
    public RegisterRepository registerRepository;

    @Override
    public ResponseEntity<Map<String, String>> addApplication(RegisterDTO registerDTO) {

        UserCredentials userCredentials = new UserCredentials();
        UserCredentials resultUserCredentials = null;
        Map<String, String> resultMap = new HashMap<>();

        userCredentials.setUserName(registerDTO.getUserName());
        userCredentials.setPassword(registerDTO.getPassword());
        userCredentials.setContactInfo(registerDTO.getContactInfo());
        userCredentials.setGender(registerDTO.getGender());
        userCredentials.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userCredentials.setSessionId(API.generateSessionId());

        resultUserCredentials = registerRepository.save(userCredentials);

        if(resultUserCredentials == null){
            resultMap.put("error", "Registration Unsuccessful");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        resultMap.put("message", "Registration Successful");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
