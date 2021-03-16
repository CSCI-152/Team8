package com.example.pdms;

public class Reservation {
    private String Date;
    private String Patient;
    private String Doctor;
    private String Hospital;

    public Reservation(String Date, String Patient, String Doctor, String Hospital) {
        this.Date = Date;
        this.Patient = Patient;
        this.Doctor = Doctor;
        this.Hospital = Hospital;
    }
    public String getDate() {
        return Date;
    }
    public String getPatient() {
        return Patient;
    }
    public String getDoctor() {
        return Doctor;
    }
    public String getHospital() {
        return Hospital;
    }
    public void changeDate(String newDate) {
        this.Date = newDate;
    }
}
