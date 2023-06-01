package com.ragul.springsecuritybasic.controller;

import com.ragul.springsecuritybasic.entity.User;
import com.ragul.springsecuritybasic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id,@RequestBody User user) {
        return userService.update(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}
