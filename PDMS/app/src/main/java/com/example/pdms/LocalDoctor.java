package com.example.pdms;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LocalDoctor implements Serializable{
    private String UID;
    private String email;
    public LocalDoctor(String UID, String email) {
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
