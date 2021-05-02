package com.example.pdms;
import org.junit.Test;

import static org.junit.Assert.*;
public class FeesTest {
    @Test
    public void setId() {
        String inputdid = "id";
        String expectedid = "id";
        String outputid;
        Fees fees = new Fees();
        fees.setId(inputdid);
        outputid = fees.getId();
        assertEquals(expectedid, outputid);
    }

    @Test
    public void setPatientId() {
        String inputpatientid= "patientid";
        String expectedpatientid= "patientid";
        String outputpatientid;
        Fees fees = new Fees();
        fees.setPatientId(inputpatientid);
        outputpatientid = fees.getPatientId();
        assertEquals(expectedpatientid,outputpatientid);
    }

    @Test
    public void setLevel() {
        String inputlevel = "levelofivisit";
        String expectedlevel= "levelofivisit";
        String outputlevel;
        Fees fees = new Fees();
        fees.setLevel(inputlevel);
        outputlevel = fees.getLevel();
        assertEquals(expectedlevel,outputlevel);
    }

    @Test
    public void setBill() {
        String inputbill = "bill";
        String expectedbill = "bill";
        String outputbill;
        Fees fees = new Fees();
        fees.setBill(inputbill);
        outputbill = fees.getBill();
        assertEquals(expectedbill,outputbill);



    }
}
