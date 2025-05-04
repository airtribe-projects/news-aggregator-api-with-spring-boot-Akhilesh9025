package com.airtribe.project.Controller;

import com.airtribe.project.DTO.RegisterRequest;
import com.airtribe.project.Service.UserService;
import com.airtribe.project.Util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    private final JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
        this.jwtUtil = new JwtUtil();
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username) {
        return userService.findByUsername(username).toString();
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @PutMapping("/{username}/roles")
    public void updateUserRoles(@PathVariable String username, @RequestBody Set<String> roles) {
        userService.updateUserRoles(username, roles);
    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterRequest request) {
        boolean isValidUser = userService.login(request.getUsername(), request.getPassword());

        if (isValidUser) {
            return jwtUtil.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @GetMapping("/id/{id}")
    public String getUserById(@PathVariable Long id) {
        return userService.getUserById(id).toString();
    }

    @GetMapping("/all")
    public String getAllUsers() {
        return userService.getAllUsers().toString();
    }

    @GetMapping("/preferences/{userId}")
    public String getUserPreferences(@PathVariable Long userId) {
        return userService.getUserPreferences(userId).toString();
    }

    @PutMapping("/preferences/{userId}")
    public void updateUserPreferences(@PathVariable Long userId, @RequestBody Set<String> preferences) {
        userService.updateUserPreferences(userId, preferences);
    }
}
