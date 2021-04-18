package com.example.pdms;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Doctor implements Serializable{
    private String uid;
    private String name;
    private String email;
    private Doctor() {}
    public Doctor(String UID, String email) {
        this.uid = uid;
        this.email = email;
    }
    public String getUID() {
        return this.uid;
    }
    public String getEmail() {
        return this.email;
    }
    public String getName() {
        return this.name;
    }
    public void setUID(String UID) {
        this.uid = UID;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {this.name = name;}
}
