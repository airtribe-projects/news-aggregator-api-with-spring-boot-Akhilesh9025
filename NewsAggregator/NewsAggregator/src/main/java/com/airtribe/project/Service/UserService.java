package com.airtribe.project.Service;

import com.airtribe.project.DTO.UserDTO;
import com.airtribe.project.Entity.User;
import com.airtribe.project.Repository.UserRepository;
import com.airtribe.project.DTO.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of("ROLE_USER")); // default role
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUserRoles(String username, Set<String> roles) {
        User user = findByUsername(username);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = findByUsername(username);
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Object getAllUsers() {
        return userRepository.findAll();
    }

    public Object getUserPreferences(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPreferences();
    }

    public void updateUserPreferences(Long userId, Set<String> preferences) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPreferences(preferences);
        userRepository.save(user);
    }

    public User registerUser(User newUser) {
        // Check if the user already exists
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        newUser.setRoles(Set.of("ROLE_USER"));

        return userRepository.save(newUser);
    }

    public Optional<UserDTO> getByUsername(String driverUser) {
        return userRepository.findByUsername(driverUser)
                .map(user -> {
                    UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getRoles());
                    return userDTO;
                });
    }

    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
