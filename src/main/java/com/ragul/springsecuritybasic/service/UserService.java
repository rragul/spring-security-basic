package com.ragul.springsecuritybasic.service;

import com.ragul.springsecuritybasic.entity.Role;
import com.ragul.springsecuritybasic.entity.User;
import com.ragul.springsecuritybasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if(existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User update(Integer id,User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        assert existingUser != null;
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(existingUser);
    }
}
