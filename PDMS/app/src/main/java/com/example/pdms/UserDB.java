package com.example.pdms;

public class UserDB {

    private String Email;
    private String Name;

    public UserDB(String Email, String Name) {
        this.Email = Email;
        this.Name = Name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
};
