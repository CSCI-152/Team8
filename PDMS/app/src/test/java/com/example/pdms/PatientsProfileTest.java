package com.example.pdms;

import org.junit.Test;

import static org.junit.Assert.*;

public class PatientsProfileTest {

    @Test
    public void setName() {
        String InputName = "MyName";
        String ExpectedName = "MyName";
        String OutputName;
        PatientsProfile patient = new PatientsProfile();
        patient.setName(InputName);
        OutputName = patient.getName();
        assertEquals(ExpectedName,OutputName);

    }

    @Test
    public void setDOB() {
        String InputDOB = "00/00/0000";
        String ExpectedDOB = "00/00/0000";
        String OutputDOB;
        PatientsProfile patient = new PatientsProfile();
        patient.setDOB(InputDOB);
        OutputDOB = patient.getDOB();
        assertEquals(ExpectedDOB,OutputDOB);
    }

    @Test
    public void setReferredBy() {
        String InputRef = "Doctor";
        String ExpectedRef = "Doctor";
        String OutputRef;
        PatientsProfile patient = new PatientsProfile();
        patient.setReferredBy(InputRef);
        OutputRef = patient.getReferredBy();
        assertEquals(ExpectedRef,OutputRef);
    }

    @Test
    public void setBloodGroup() {
        String InputBlood = "AB";
        String ExpectedBlood = "AB";
        String OutputBlood;
        PatientsProfile patient = new PatientsProfile();
        patient.setBloodGroup(InputBlood);
        OutputBlood = patient.getBloodGroup();
        assertEquals(ExpectedBlood,OutputBlood);
    }

    @Test
    public void setPhoneNum() {
        String InputPhone = "000-000-0000";
        String ExpectedPhone = "000-000-0000";
        String OutputPhone;
        PatientsProfile patient = new PatientsProfile();
        patient.setPhoneNum(InputPhone);
        OutputPhone = patient.getPhoneNum();
        assertEquals(ExpectedPhone,OutputPhone);
    }

    @Test
    public void setMedicalHistory() {
        String InputMed = "High Blood Pressure";
        String ExpectedMed = "High Blood Pressure";
        String OutputMed;
        PatientsProfile patient = new PatientsProfile();
        patient.setMedicalHistory(InputMed);
        OutputMed = patient.getMedicalHistory();
        assertEquals(ExpectedMed,OutputMed);
    }
}