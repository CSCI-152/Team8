package com.example.pdms;

public class UserDB {
    private String Email;
    private String Name;
    private String UID;

    public UserDB(String Email, String Name, String UID) {
        this.Email = Email;
        this.Name = Name;
        this.UID = UID;
    }

    public UserDB(String Email, String Name) {
        this.Email = Email;
        this.Name = Name;
    }

    public  UserDB(){}

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
    public void setUID(String UID) {
        this.UID = UID;
    }
    public String getUID() {
        return this.UID;
    }
};
