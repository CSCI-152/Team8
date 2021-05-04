package com.example.pdms;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrescriptionTest {
    @Test
    public void setDoctorname() {
        String inputddoctorname = "Doctorname";
        String expecteddoctorname = "Doctorname";
        String ouputdoctorname;
        Prescription prescription = new Prescription();
        prescription.setDoctorname(inputddoctorname);
        ouputdoctorname = prescription.getDoctorname();
        assertEquals(expecteddoctorname, ouputdoctorname);
    }
    @Test
    public void setDoctoraddress() {
        String inputdoctoraddress = "Doctoraddress";
        String expecteddoctoraddress = "Doctoraddress";
        String outputdoctoraddress;
        Prescription prescription = new Prescription();
        prescription.setDoctoraddress(inputdoctoraddress);
        outputdoctoraddress = prescription.getDoctoraddress();
        assertEquals(expecteddoctoraddress,outputdoctoraddress);
    }
    @Test
    public void setPatientname() {
        String inputpatientname = "patientname";
        String expectedpatientname = "patientname";
        String outputpatientname;
        Prescription prescription = new Prescription();
        prescription.setPatientname(inputpatientname);
        outputpatientname = prescription.getPatientname();
        assertEquals(expectedpatientname,outputpatientname);
    }
    @Test
    public void setPhonenumber() {
        String inputphonenumber = "000-000-0000";
        String expectedphonenumber = "000-000-0000";
        String outputphonenumber;
        Prescription prescription = new Prescription();
        prescription.setPhonenumber(inputphonenumber);
        outputphonenumber = prescription.getPhonenumber();
        assertEquals(expectedphonenumber,outputphonenumber);
    }
    @Test
    public void setAge() {
        String inputage = "patientage";
        String expectedage = "patientage";
        String outputage;
        Prescription prescription = new Prescription();
        prescription.setAge(inputage);
        outputage = prescription.getAge();
        assertEquals(expectedage, outputage);
    }
    @Test
    public void setGender() {
        String inputgender = "Male/Female";
        String expectedgender = "Male/Female";
        String outputgender;
        Prescription prescription = new Prescription();
        prescription.setGender(inputgender);
        outputgender = prescription.getGender();
        assertEquals(expectedgender, outputgender);
    }
    @Test
    public void setMedication() {
        String inputmedication = "patientmedication";
        String expectedmedication = "patientmedication";
        String outputmedication;
        Prescription prescription = new Prescription();
        prescription.setMedication(inputmedication);
        outputmedication = prescription.getMedication();
        assertEquals(expectedmedication, outputmedication);
    }
    @Test
    public void setSignature() {
        String inputsignature = "doctorsignature";
        String expectedsignature = "doctorsignature";
        String outputsignature;
        Prescription prescription = new Prescription();
        prescription.setSignature(inputsignature);
        outputsignature = prescription.getSignature();
        assertEquals(expectedsignature, outputsignature);
    }
    @Test
    public void setDate() {
        String inputdate = "patientdate";
        String expecteddate = "patientdate";
        String outputdate;
        Prescription prescription = new Prescription();
        prescription.setDate(inputdate);
        outputdate = prescription.getDate();
        assertEquals(expecteddate, outputdate);
    }


}
