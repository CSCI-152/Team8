package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientDashboard extends AppCompatActivity {
    Button btn_profile, btn_settings, btn_calendarandreservation,btn_search, btn_contact, btn_about, btn_bill, btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);
        btn_profile = (Button)findViewById(R.id.buttonPROFILE);
        btn_settings = (Button)findViewById(R.id.buttonSETTINGS);
        btn_calendarandreservation = (Button)findViewById(R.id.buttonCALENDARANDRESERVATION);
        btn_search = (Button)findViewById(R.id.buttonSEARCH);
        btn_logout = (Button)findViewById(R.id.buttonLOGOUT);
        btn_contact = (Button)findViewById(R.id.buttonCONTACTUS);
        btn_about = (Button)findViewById(R.id.buttonABOUTUS);
        btn_bill = (Button)findViewById(R.id.buttonBILL);

        btn_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "FIND BILL", Toast.LENGTH_SHORT).show();
            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "GO TO SETTINGS", Toast.LENGTH_SHORT).show();
            }
        });
        btn_calendarandreservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "RESERVE APPOINTMENT", Toast.LENGTH_SHORT).show();
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "CONTACT US", Toast.LENGTH_SHORT).show();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "GO TO SEARCH", Toast.LENGTH_SHORT).show();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "SIGN OUT", Toast.LENGTH_SHORT).show();
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientDashboard.this, "LEARN ABOUT US", Toast.LENGTH_SHORT).show();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PatientDashboard.this, "GO TO YOUR PROFILE", Toast.LENGTH_SHORT).show();
                Intent toProfile = new Intent(PatientDashboard.this, Patient_Profile.class);
                startActivity(toProfile);
            }
        });

    }
}