package com.itgirls.socialMedia.service;

import com.itgirls.socialMedia.models.User;
import com.itgirls.socialMedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addNewUser(User user) {
        return User.registerNewUser(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword());
    }

    public List<User> getAllUsers() {
        System.out.println("Get all users");
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) {
        System.out.println("Get user with id " + id);
        return userRepository.getUserById(id);
    }

    public User getUserByName(String userName) {
        System.out.println("Get user by name " + userName);
        return null;
    }
}
