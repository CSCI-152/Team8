package com.example.pdms;
import android.os.Build;

import java.util.Date;
import java.time.LocalDateTime;

import androidx.annotation.RequiresApi;

public class Reservation {
    private String reservationID;
    private LocalDateTime ReservationTime;
    private String PatientID;
    private String DoctorID;
    private String Hospital;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reservation(long calendarDate, int currentHour, int currentMinute, String Patient, String Doctor, String Hospital) {
        this.reservationID = createReservationID(Patient, Doctor);
        this.ReservationTime = createReservationTime(calendarDate,currentHour,currentMinute);
        this.PatientID = Patient;
        this.DoctorID = Doctor;
        this.Hospital = Hospital;
    }
    public String getReservationID() {
        return reservationID;
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
    public String createReservationID(String userID, String doctorID){
        String reservationID = "";
        for (int i = 0; i < 14; i++) {
            reservationID = reservationID + userID.charAt(i) + doctorID.charAt(i);
        }
        return reservationID;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime createReservationTime(long dateString, int currentHour, int currentMinute){
        Date newDate = new Date(dateString);
        LocalDateTime reservationTime = LocalDateTime.of(newDate.getYear(),newDate.getMonth(),newDate.getDay(),currentHour,currentMinute);
        return reservationTime;
    }
}
