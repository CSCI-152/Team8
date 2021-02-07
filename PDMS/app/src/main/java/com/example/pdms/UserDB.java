package com.example.pdms;

public class UserDB {

    private String isUser;
    private String Email;

    public UserDB(String isUser, String email) {
        this.isUser = isUser;
        Email = email;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    public void setEmail(String email) {
        Email = email;
    }
};
