package com.itgirls.socialMedia.controller;

import com.itgirls.socialMedia.dto.UserDto;
import com.itgirls.socialMedia.entity.User;
import com.itgirls.socialMedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String getAllUsers(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email,
                                  Model model) {

        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(email)) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "index";
        } else {
            return userService.getUsersByNameOrEmail(name, email).toString();
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

    @PostMapping("")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addNewUser(user));
    }

    @GetMapping("/followers")
    public List<UserDto> getAllUsersWithFollowers() {
        return userService.getAllUsersWithFollowers();
    }

    @GetMapping("/count")
    public ResponseEntity<?> getUsersCount() {
        return ResponseEntity.ok().body(userService.getUsersCount());
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

}
