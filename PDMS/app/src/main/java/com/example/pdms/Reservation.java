package com.example.pdms;
import android.os.Build;

import java.util.Date;
import java.time.LocalDateTime;

import androidx.annotation.RequiresApi;

public class Reservation {
    private String ReservationID;
    private LocalDateTime ReservationTime;
    private String PatientID;
    private String DoctorID;
    private String Hospital;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reservation(Date calendarDate, int currentHour, int currentMinute, String Patient, String Doctor, String Hospital) {
        this.ReservationID = createReservationID(Patient, Doctor);
        this.ReservationTime = createReservationTime(calendarDate,currentHour,currentMinute);
        this.PatientID = Patient;
        this.DoctorID = Doctor;
        this.Hospital = Hospital;
    }
    public String getReservationID() {
        return ReservationID;
    }
    public LocalDateTime getReservationTime() {
        return ReservationTime;
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

    public void changeDate(LocalDateTime newDate) {
        this.ReservationTime = newDate;
    }
    private String createReservationID(String userID, String doctorID){
        String reservationID = "";
        for (int i = 0; i < 14; i++) {
            reservationID = reservationID + userID.charAt(i) + doctorID.charAt(i);
        }
        return reservationID;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDateTime createReservationTime(Date calendarDate, int currentHour, int currentMinute){
        LocalDateTime reservationTime = LocalDateTime.of(calendarDate.getYear(),calendarDate.getMonth(),calendarDate.getDay(),currentHour,currentMinute);
        return reservationTime;
    }
}
