package com.itgirls.socialMedia.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class SocialMediaUser {

    private String userName;
    private String password;
    private String email;

    public abstract void sendMessage(String message);

    public void logIn() {
        System.out.println("User " + userName + " log in");
    }

    public void logOut() {
        System.out.println("User " + userName + " log out");
    }

}
