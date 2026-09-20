package com.example.basicauthentication.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.basicauthentication.entity.User;
import com.example.basicauthentication.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register User
    public User registerUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // Get User By ID
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    // Update User
    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElse(null);

        if (existingUser == null) {
            return null;
        }

        existingUser.setUsername(updatedUser.getUsername());

        if (updatedUser.getPassword() != null
                && !updatedUser.getPassword().isBlank()) {

            existingUser.setPassword(
                    passwordEncoder.encode(
                            updatedUser.getPassword()
                    )
            );
        }

        return userRepository.save(existingUser);
    }

    // Delete User
    public void deleteUser(Long id) {

        if (userRepository.existsById(id)) {

            userRepository.deleteById(id);
        }
    }
}