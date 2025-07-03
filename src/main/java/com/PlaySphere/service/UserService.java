package com.PlaySphere.service;

import com.PlaySphere.dto.UserRequestDTO;
import com.PlaySphere.dto.UserResponseDTO;
import com.PlaySphere.entity.User;
import com.PlaySphere.repository.UserRepository;
import com.PlaySphere.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // 🔹 Register new user
    public UserResponseDTO register(UserRequestDTO requestDTO) {
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword())); // 🔐 Hashed

        user = userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail()); // 🔐 JWT token

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), token);
    }

    // 🔹 Login existing user
    public UserResponseDTO login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail()); // 🔐 JWT token
                return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), token);
            }
        }

        throw new RuntimeException("Invalid email or password");
    }
}

