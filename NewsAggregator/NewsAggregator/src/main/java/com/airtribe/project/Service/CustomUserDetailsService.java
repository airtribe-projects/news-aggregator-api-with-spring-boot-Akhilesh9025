package com.airtribe.project.Service;

import com.airtribe.project.Repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.airtribe.project.Entity.User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            com.airtribe.project.Entity.User foundUser = user.get();
            return User.withUsername(foundUser.getUsername())
                    .password(foundUser.getPassword())
                    .roles(foundUser.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

