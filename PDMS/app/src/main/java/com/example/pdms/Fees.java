package com.example.pdms;

public class Fees {

private String id, patientId, level, bill;

    public Fees(String bill, String patientId, String level, String id) {
        this.bill = bill;
        this.patientId = patientId;
        this.level = level;
        this.id = id;
    }

    public Fees(){}

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
