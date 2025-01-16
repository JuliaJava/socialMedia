package com.itgirls.socialMedia.controller;

import com.itgirls.socialMedia.dto.UserDto;
import com.itgirls.socialMedia.entity.User;
import com.itgirls.socialMedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email) {
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(email)) {
            return userService.getAllUsers();
        } else {
            return userService.getUsersByNameOrEmail(name, email);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Exception while get user by id");
        }
    }

    @PostMapping()
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/followers")
    public List<UserDto> getAllUsersWithFollowers() {
        return userService.getAllUsersWithFollowers();
    }

    @GetMapping("/count")
    public ResponseEntity<?> getUsersCount() {
        return ResponseEntity.ok().body(userService.getUsersCount());
    }

}
