

package com.example.pdms;

public class Reservation {
    private String ReservationID;
    private String ReservationDate;
    private String ReservationHM;
    private String PatientID;
    private String DoctorID;
    private String Hospital;


    private Reservation() {}
    public Reservation(String Patient) {
        this.PatientID = Patient;
    }
    public String getReservationID() {
        return ReservationID;
    }

    public void setDoctorID(String doctorID) {
        this.DoctorID = doctorID;
    }
    public void setHospital(String hospital) {
        this.Hospital = hospital;
    }
    public void setReservationDate(int year, int month, int day) {
        this.ReservationDate = year + "/" + String.format("%02d",month) + "/" + String.format("%02d",day);
    }
    public void setReservationHM(int hour, int minute) {
        this.ReservationHM = String.format("%02d",hour) + ":" + String.format("%02d",minute); //pad with '0' so that '7' will show up as '07'
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

    public String getReservationDate() {
        return ReservationDate;
    }
    public String getReservationHM() {
        return ReservationHM;
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