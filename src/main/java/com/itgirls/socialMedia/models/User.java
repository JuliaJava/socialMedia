package com.itgirls.socialMedia.models;

import com.itgirls.socialMedia.exceptions.CustomException;
import com.itgirls.socialMedia.interfaces.AuthorizationInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class User extends SocialMediaUser implements AuthorizationInterface {

    private Long userId;

    public User(Long userId, String userName, String mail, String password) {
        super(userName, mail,password);
        this.userId = userId;
    }

    public static User registerNewUser(Long userId, String userName, String mail, String password) {
        try {
            validatePassword(password);
        } catch (CustomException e) {
            log.error("Can't create user {}. Error: {}", userName, e.getMessage());
            System.out.println("Can't create user " + userName + ". Error: " + e.getMessage());
            throw new RuntimeException();
        }

        User user = new User(userId, userName, mail, password);
        System.out.println("Create user " + userName);

        return user;
    }

    private static void validatePassword(String password) throws CustomException {
        if (password == null || password.length() < 6) {
            throw new CustomException("Password too short");
        }
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Send message " + message);
    }


    private boolean isPasswordCorrect(String password) {
        boolean isPasswordCorrect = getPassword().equals(password);
        String message = isPasswordCorrect ? "Password is correct" : "Password is not correct";
        System.out.println(message);
        return isPasswordCorrect;
    }

    @Override
    public void setNewPassword(String password) {
        if (password.length() >= 10 && password.length() < 20) {
            System.out.println("It is a perfect choice " + getUserName() + "! Password successfully change");
            setPassword(password);
        } else {
            System.out.println("It's too short password, let's try again!");
        }
    }

    @Override
    public String checkPassword(String password) {
        return "Is password correct - " + isPasswordCorrect(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + getUserName() + '\'' +
                ", mail='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
