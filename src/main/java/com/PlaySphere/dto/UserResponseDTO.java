package com.PlaySphere.dto;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String token; // 🔐 Added for JWT token

    // 🔹 Default constructor
    public UserResponseDTO() {}

    // 🔹 Constructor without token
    public UserResponseDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // 🔹 Constructor with token
    public UserResponseDTO(Long id, String username, String email, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
    }

    // 🔹 Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
