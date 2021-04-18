package com.example.pdms;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LocalDoctor implements Serializable{
    private String UID;
    private String email;
    private LocalDoctor() {}
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
    public void setUID(String UID) {
        this.UID = UID;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
