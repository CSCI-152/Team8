package com.example.pdms;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Doctor implements Serializable{
    private String UID;
    private String email;
    public Doctor(String UID, String email) {
        this.UID = UID;
        this.email = email;
    }
    public String getUID() {
        return this.UID;
    }
    public String getEmail() {
        return this.email;
    }
}
