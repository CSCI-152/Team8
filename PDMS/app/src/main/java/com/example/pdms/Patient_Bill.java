package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Patient_Bill extends AppCompatActivity {

    private TextView TotalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__bill);

        TotalBill = findViewById(R.id.Total_Bill_Value);
    }
}