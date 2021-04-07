package com.example.pdms;

public class Fees {
    private String id;
    private String patientId;
    private String level;
    private String bill;

    public Fees() {

    }

    public Fees(String id, String patientId, String level, String bill) {
        this.id = id;
        this.patientId = patientId;
        this.level = level;
        this.bill = bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
