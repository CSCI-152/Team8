package com.example.pdms;

public class PatientsProfile {
    private String Name;
    private String DOB;
    private String ReferredBy;
    private String BloodGroup;
    private String PhoneNum;

    public PatientsProfile() {

    }

    public PatientsProfile(String name, String DOB, String referredBy, String bloodGroup, String phoneNum) {
        this.Name = name;
        this.DOB = DOB;
        this.ReferredBy = referredBy;
        this.BloodGroup = bloodGroup;
        this.PhoneNum = phoneNum;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getReferredBy() {
        return ReferredBy;
    }

    public void setReferredBy(String referredBy) {
        ReferredBy = referredBy;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }
}
