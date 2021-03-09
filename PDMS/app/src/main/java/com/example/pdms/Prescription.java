package com.example.pdms;


public class Prescription {
    private String doctorname;
    private String doctoraddress;
    private String patientname;
    private String phonenumber;
    private String address;
    private String age;
    private String gender;
    private String note;
    private String signature;
    private String date;

    public Prescription() {

    }
    public Prescription(String doctorname, String doctoraddress, String patientname, String phonenumber, String address, String age, String gender, String note, String signature, String date) {
        doctorname = doctorname;
        doctoraddress = doctoraddress;
        patientname = patientname;
        phonenumber = phonenumber;
        address = address;
        age = age;
        gender = gender;
        note = note;
        signature = signature;
        date = date;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctoraddress() {
        return doctoraddress;
    }

    public void setDoctoraddress(String doctoraddress) {
        this.doctoraddress = doctoraddress;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

