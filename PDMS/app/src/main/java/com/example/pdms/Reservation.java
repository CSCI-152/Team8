package com.example.pdms;

public class Reservation {
    private String DoctorID;
    private String Hospital;
    private String PatientID;
    private String ReservationDate;
    private String ReservationHM;
    private String ReservationID;

    private Reservation() {}
    public Reservation(String PatientID) {
        this.PatientID = PatientID;
    }
    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }
    public void setHospital(String Hospital) {
        this.Hospital = Hospital;
    }
    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }
    public void setReservationDate(String ReservationDate) {
        this.ReservationDate = ReservationDate;
    }
    public void setReservationHM(String ReservationHM) {
        this.ReservationHM = ReservationHM;
    }
    public void setReservationID(String ReservationID) {
        this.ReservationID = ReservationID;
    }
    public void initReservationDate(int year, int month, int day) {
        this.ReservationDate = year + "/" + String.format("%02d",month) + "/" + String.format("%02d",day);
    }
    public void initReservationHM(int hour, int minute) {
        this.ReservationHM = String.format("%02d",hour) + ":" + String.format("%02d",minute); //pad with '0' so that '7' will show up as '07'
    }

    public String getDoctorID() {
        return DoctorID;
    }
    public String getHospital() {
        return Hospital;
    }
    public String getPatientID() {
        return PatientID;
    }
    public String getReservationDate() {
        return ReservationDate;
    }
    public String getReservationHM() {
        return ReservationHM;
    }
    public String getReservationID() {
        return ReservationID;
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
        this.ReservationID = createReservationID(this.PatientID, this.DoctorID);
    }
    private String createReservationID(String userID, String doctorID){
        String reservationID = "";
        for (int i = 0; i < 14; i++) {
            reservationID = reservationID + userID.charAt(i) + doctorID.charAt(i);
        }
        return reservationID;
    }
}