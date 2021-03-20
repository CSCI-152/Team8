package com.example.pdms;

public class Reservation {
    private String Date;
    private String PatientID;
    private String DoctorID;
    private String Hospital;

    public Reservation(String Date, String Patient, String Doctor, String Hospital) {
        this.Date = Date;
        this.PatientID = Patient;
        this.DoctorID = Doctor;
        this.Hospital = Hospital;
    }
    public String getDate() {
        return Date;
    }
    public String getPatientID() {
        return PatientID;
    }
    public String getDoctorID() {
        return DoctorID;
    }
    public String getHospital() {
        return Hospital;
    }
    public void changeDate(String newDate) {
        this.Date = newDate;
    }
}
