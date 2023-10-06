package com.advarra.badminton.services.interfaces;

import com.advarra.badminton.dtos.LoginDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    ResponseEntity<Map<String, String>> userLogin(LoginDTO loginDTO) throws Exception;
}
