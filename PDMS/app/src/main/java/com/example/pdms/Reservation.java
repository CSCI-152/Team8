

package com.example.pdms;

public class Reservation {
    private String reservationID;
    private String reservationDate;
    private String reservationHM;
    private String patientID;
    private String doctorID;
    private String hospital;


    private Reservation() {}

    public Reservation(String ReservationID, String ReservationDate, String ReservationHM, String PatientID, String DoctorID, String Hospital) {
        this.reservationID = ReservationID;
        this.reservationDate = ReservationDate;
        this.reservationHM = ReservationHM;
        this.patientID = PatientID;
        this.doctorID = DoctorID;
        this.hospital = Hospital;
    }

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

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    public void setReservationDate(int year, int month, int day) {
        this.reservationDate = year + "/" + String.format("%02d",month) + "/" + String.format("%02d",day);
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
}