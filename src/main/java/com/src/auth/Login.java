package com.src.auth;

public class Login {
   
    public boolean handleLogin(String username, String password) {
        boolean isLoggedIn = DatabaseAccessManager.checkLogin(username, password);
        return isLoggedIn;
    }
 
}
