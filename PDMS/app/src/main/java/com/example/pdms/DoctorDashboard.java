package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorDashboard extends AppCompatActivity {
    Button btn_appointment, btn_chart, btn_medication, btn_logout, btn_hospital;
    TextView txt_doctorName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        btn_appointment = (Button)findViewById(R.id.buttonAPPOINTMENT);
        btn_chart = (Button)findViewById(R.id.buttonCHART);
        btn_logout = (Button)findViewById(R.id.buttonLOGOUT);
        btn_medication = (Button)findViewById(R.id.buttonMEDICATION);
        btn_hospital = (Button)findViewById(R.id.buttonHospital);
        txt_doctorName = (TextView)findViewById(R.id.txt_doctorName);

        setTxtDoctorName(txt_doctorName);

        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent toAppointments = new Intent(DoctorDashboard.this, DoctorAppointments.class);
                startActivity(toAppointments);
            }
        });
        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toChart = new Intent(DoctorDashboard.this, Patient_Chart.class);
                startActivity(toChart);

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

        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Doctor_Hospital = new Intent(DoctorDashboard.this, DoctorHospital.class);
                startActivity(Doctor_Hospital);
            }
        });
    }

    private void setTxtDoctorName(TextView txt_patientName) {
        DatabaseReference patientRef = FirebaseDatabase.getInstance().getReference("Doctors")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
                if(snapshot.exists()) {
                    Doctor newDoctor = snapshot.getValue(Doctor.class);
                    txt_patientName.setText("Welcome, " + newDoctor.getName());
                }
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
