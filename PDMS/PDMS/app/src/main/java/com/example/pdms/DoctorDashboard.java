package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DoctorDashboard extends AppCompatActivity {
    Button btn_appointment, btn_chart, btn_medication, btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        btn_appointment = (Button)findViewById(R.id.buttonAPPOINTMENT);
        btn_chart = (Button)findViewById(R.id.buttonCHART);
        btn_logout = (Button)findViewById(R.id.buttonLOGOUT);
        btn_medication = (Button)findViewById(R.id.buttonMEDICATION);

        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorDashboard.this, "GO TO APPOINTMENT", Toast.LENGTH_SHORT).show();
            }
        });
        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorDashboard.this, "GO TO CHART", Toast.LENGTH_SHORT).show();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorDashboard.this, "SIGN OUT", Toast.LENGTH_SHORT).show();
            }
        });
        btn_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorDashboard.this, "GO TO MEDICATIONS", Toast.LENGTH_SHORT).show();
            }
        });
    }
}