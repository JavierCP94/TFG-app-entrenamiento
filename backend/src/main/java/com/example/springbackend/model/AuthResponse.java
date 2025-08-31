package com.example.springbackend.model;

public class AuthResponse {
    private String token;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String message;
    private boolean success;

    // -----------------------------
    // Constructores
    // -----------------------------

    // Respuesta de error
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Respuesta de éxito con token y datos de usuario
    public AuthResponse(boolean success, String token, String username, String email,
            String firstName, String lastName) {
        this.success = success;
        this.token = token;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = success ? "Authentication successful" : "Authentication failed";
    }

    // -----------------------------
    // Getters & Setters
    // -----------------------------
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
