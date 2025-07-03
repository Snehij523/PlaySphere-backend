package com.PlaySphere.controller;

import com.PlaySphere.dto.UserRequestDTO;
import com.PlaySphere.dto.UserResponseDTO;
import com.PlaySphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO requestDTO) {
        return userService.register(requestDTO);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody UserRequestDTO requestDTO) {
        return userService.login(requestDTO.getEmail(), requestDTO.getPassword());
    }
}
