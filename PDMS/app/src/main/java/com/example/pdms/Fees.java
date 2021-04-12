package com.example.pdms;

public class Fees {

    private String bill, id, level, patientId;

    public Fees(String bill, String id, String level, String patientId) {
        this.bill = bill;
        this.id = id;
        this.level = level;
        this.patientId = patientId;
    }

    public Fees(){}

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
