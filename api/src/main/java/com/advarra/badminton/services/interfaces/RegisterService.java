package com.advarra.badminton.services.interfaces;

import com.advarra.badminton.dtos.LoginDTO;
import com.advarra.badminton.dtos.RegisterDTO;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RegisterService {

    ResponseEntity<Map<String, String>> addApplication(RegisterDTO registerDTO);
}
