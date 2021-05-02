package com.example.pdms;
import org.junit.Test;
import static org.junit.Assert.*;
public class PatientTest {
    @Test
    public void setId() {
        String inputid= "patientid";
        String expectedid= "patientid";
        String outputid;
        Patient patient = new Patient();
        patient.setId(inputid);
        outputid = patient.getId();
        assertEquals(expectedid, outputid);
    }

    @Test
    public void setUsername() {
        String inputusername = "patientusername";
        String expectedusername = "patientusername";
        String outputusername;
        Patient patient = new Patient();
        patient.setUsername(inputusername);
        outputusername = patient.getUsername();
        assertEquals(expectedusername, outputusername);
    }

    @Test
    public void setPassword() {
        String inputpassword= "patientpassword";
        String expectedpassword= "patientpassword";
        String outputpassword;
        Patient patient = new Patient();
        patient.setPassword(inputpassword);
        outputpassword = patient.getPassword();
        assertEquals(expectedpassword, outputpassword);
    }
}
