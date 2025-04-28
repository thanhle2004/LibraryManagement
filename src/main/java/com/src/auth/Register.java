package com.src.auth;

public class Register {

    public boolean handleRegister(String username, String password) {
        boolean isLoggedIn = DatabaseAccessManager.registerUser(username, password);
        return isLoggedIn;
    }

}
