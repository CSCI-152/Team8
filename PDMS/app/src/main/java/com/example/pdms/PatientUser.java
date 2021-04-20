package com.example.pdms;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PatientUser implements Serializable {
    private String email;
    private String name;
    private String uid;
    private PatientUser() {}
    public PatientUser(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getUid() {
        return uid;
    }
}
