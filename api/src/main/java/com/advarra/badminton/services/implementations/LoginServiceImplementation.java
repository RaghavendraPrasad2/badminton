package com.advarra.badminton.services.implementations;

import com.advarra.badminton.api.API;
import com.advarra.badminton.dtos.LoginDTO;
import com.advarra.badminton.model.UserCredentials;
import com.advarra.badminton.repositories.RegisterRepository;
import com.advarra.badminton.services.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImplementation implements LoginService {

    @Autowired
    public RegisterRepository registerRepository;

    @Override
    public ResponseEntity<Map<String, String>> userLogin(LoginDTO loginDTO) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        UserCredentials userCredentials = registerRepository.findByContactInfo(loginDTO.getEmail());

        if(userCredentials == null){
            resultMap.put("error", "User Not Found");
            return new ResponseEntity<>(resultMap, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        else{
            if(!Objects.equals(userCredentials.getPassword(), loginDTO.getPassword())){
                resultMap.put("error", "Invalid Credentials");
                return new ResponseEntity<>(resultMap, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else{
                String sessionKey = userCredentials.getSessionId();
//                String encryptedSessionKey = API.encryptSessionId(sessionKey);
                resultMap.put("sessionKey", sessionKey);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
    }
}
