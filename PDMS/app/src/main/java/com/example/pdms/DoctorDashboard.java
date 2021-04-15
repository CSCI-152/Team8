package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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
                Intent doctorAppointment = new Intent(DoctorDashboard.this, DoctorAppointments.class);
                startActivity(doctorAppointment);
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
                Intent Doctor_logout = new Intent(DoctorDashboard.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(Doctor_logout);
                finish();
            }
        });
        btn_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Doctor_prescription = new Intent(DoctorDashboard.this, DoctorPrescription.class);
                startActivity(Doctor_prescription);
            }
        });
    }
}