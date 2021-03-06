package com.example.pdms;

import android.os.Build;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import androidx.annotation.RequiresApi;

@SuppressWarnings("serial")
public class Reservation implements Serializable {
    private String reservationID;
    private String reservationDate;
    private String reservationHM;
    private String patientID;
    private String doctorID;
    private String hospital;
  
    private Reservation() {}
    public Reservation(String Patient) {
        this.patientID = Patient;
    }
    public String getReservationID() {
        return reservationID;
    }
    public String getReservationDate() {
        return reservationDate;
    }
    public String getReservationHM() {
        return reservationHM;
    }
    public String getPatientID() {
        return patientID;
    }
    public String getDoctorID() {
        return doctorID;
    }
    public String getHospital() {
        return hospital;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    public void resetReservationDateAndTime(){
        this.reservationDate = null;
        this.reservationHM = null;
    }
    public void setReservationDate(int year, int month, int day) {
        this.reservationDate = year + "-" + String.format("%02d",month) + "-" + String.format("%02d",day);
    }
    public void setReservationHM(int hour, int minute) {
        this.reservationHM = String.format("%02d",hour) + ":" + String.format("%02d",minute); //pad with '0' so that '7' will show up as '07'
    }
    public String printReservationDateFormatted() {
        //returns date as dd/mm/yyyy, hh:mm
        String initYear = this.getReservationDate();
        String returnDate = initYear.substring(5,7) + "/" +  // mm
                initYear.substring(8,10) + "/" + // dd
                initYear.substring(0,4) + ", " + // yyyy
                this.getReservationHM();         // hh:mm
        return returnDate;
    }
    public Boolean verifyReservation() {
        if(getHospital() == null || getReservationDate() == null || getReservationHM() == null) {
            return false;
        }
        return true;
    }
    public void finalizeReservation() {
        this.reservationID = createReservationID(this.patientID, this.doctorID);
    }
    private String createReservationID(String userID, String doctorID){
        String reservationID = "";
        for (int i = 0; i < 14; i++) {
            reservationID = reservationID + userID.charAt(i) + doctorID.charAt(i);
        }
        return reservationID;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime reservationAsDateTime() {
        String str = this.getReservationDate() + " " + this.getReservationHM();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str,formatter);
        return dateTime;
    }
}
    