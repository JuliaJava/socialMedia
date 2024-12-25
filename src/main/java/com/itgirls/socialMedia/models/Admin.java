package com.itgirls.socialMedia.models;


import com.itgirls.socialMedia.interfaces.AuthorizationInterface;

import java.util.Scanner;

public class Admin extends SocialMediaUser implements AuthorizationInterface {

    public Group group;

    public Admin(String name, String password, String email) {
        super(name, email, password);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Admin " + getUserName() + " send message: " + message);
    }

    @Override
    public void setNewPassword(String password) {
        System.out.println("Is new password correct - " + (password.length() >= 10));
        setPassword(password);
        System.out.println("Admin " + getUserName() + " set new password successfully");
    }

    @Override
    public String checkPassword(String passwordOld) {
        Scanner scanner = new Scanner(System.in);
        String password;
        boolean isAuthSuccessfull;
        do {
            System.out.println("Fill password: ");
            password = scanner.nextLine();
            isAuthSuccessfull = getPassword().equals(password);
            if (isAuthSuccessfull) {
                System.out.println("Password is correct");
            } else {
                System.out.println("Password is not correct, try again");
            }
        } while (!isAuthSuccessfull);
        return "Password correct";
    }

}