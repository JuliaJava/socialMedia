package com.itgirls.socialMedia.controller;

import com.itgirls.socialMedia.dto.UserDto;
import com.itgirls.socialMedia.entity.User;
import com.itgirls.socialMedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Exception while get user by id");
        }
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/users/followers")
    public List<UserDto> getAllUsersWithFollowers() {
        return userService.getAllUsersWithFollowers();
    }
/*    @DeleteMapping("/users")
    public String deleteUser(@RequestParam String name) {
        return "Delete user " + name;
    }*/
}
