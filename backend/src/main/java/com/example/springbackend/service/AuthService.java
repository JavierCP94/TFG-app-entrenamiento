package com.example.springbackend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springbackend.model.AuthResponse;
import com.example.springbackend.model.LoginRequest;
import com.example.springbackend.model.RegisterRequest;
import com.example.springbackend.model.User;
import com.example.springbackend.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        try {
            // Verificar si el usuario ya existe
            if (userRepository.existsByUsername(request.getUsername())) {
                return new AuthResponse(false, "Username already exists");
            }

            if (userRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse(false, "Email already exists");
            }

            // Crear nuevo usuario
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());

            // Guardar usuario
            User savedUser = userRepository.save(user);

            // Generar token JWT
            String token = jwtService.generateToken(savedUser.getUsername());

            return new AuthResponse(token, savedUser.getUsername(), savedUser.getEmail(), 
                                  savedUser.getFirstName(), savedUser.getLastName());

        } catch (Exception e) {
            return new AuthResponse(false, "Registration failed: " + e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            // Buscar usuario por username o email
            Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
            if (userOpt.isEmpty()) {
                userOpt = userRepository.findByEmail(request.getUsername());
            }

            if (userOpt.isEmpty()) {
                return new AuthResponse(false, "User not found");
            }

            User user = userOpt.get();

            // Verificar contraseña
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new AuthResponse(false, "Invalid password");
            }

            // Actualizar último login
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);

            // Generar token JWT
            String token = jwtService.generateToken(user.getUsername());

            return new AuthResponse(token, user.getUsername(), user.getEmail(), 
                                  user.getFirstName(), user.getLastName());

        } catch (Exception e) {
            return new AuthResponse(false, "Login failed: " + e.getMessage());
        }
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
