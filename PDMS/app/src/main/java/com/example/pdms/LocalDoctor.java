package com.example.pdms;

import java.io.Serializable;

public class LocalDoctor {
    private String UID;
    private String email;
    public LocalDoctor(String UID, String email) {
        this.UID = UID;
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
}
