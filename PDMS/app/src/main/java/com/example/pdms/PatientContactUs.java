package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_contact_us);
        Button btn_DoctorX, btn_DoctorY, btn_AdminA, btn_AdminB;
            btn_DoctorX = (Button)findViewById(R.id.btn_DoctorX);
            btn_DoctorY = (Button)findViewById(R.id.btn_DoctorY);
            btn_AdminA = (Button)findViewById(R.id.btn_AdminA);
            btn_AdminB = (Button)findViewById(R.id.btn_AdminB);
            btn_DoctorX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DoctorX.class);
                    startActivity(intent);
                }
            });
            btn_DoctorY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DoctorY.class);
                    startActivity(intent);
                }
            });
            btn_AdminA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AdminA.class);
                    startActivity(intent);
                }
            });
            btn_AdminB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AdminB.class);
                    startActivity(intent);
                }
            });
        }
    }
