package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DoctorPrescription extends AppCompatActivity {
    EditText editTextDoctorName, editTextDoctorAddress, editTextPatientName, editTextPhoneNumber, editTextAddress, editTextAge, editTextGender, editTextNote, editTextSignature, editTextDate;
    Button btnInsertPrescription;
    DatabaseReference prescriptionDbRef;
    Prescription prescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_prescription);
        editTextDoctorName = (EditText)findViewById(R.id.editTextDoctorName);
        editTextDoctorAddress = (EditText)findViewById(R.id.editTextDoctorAddress);
        editTextPatientName = (EditText)findViewById(R.id.editTextPatientName);
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextAge = (EditText)findViewById(R.id.editTextAge);
        editTextGender = (EditText)findViewById(R.id.editTextGender);
        editTextNote = (EditText)findViewById(R.id.editTextNote);
        editTextSignature = (EditText)findViewById(R.id.editTextSignature);
        editTextDate = (EditText)findViewById(R.id.editTextDate);
        btnInsertPrescription = (Button)findViewById(R.id.btn_add);
        prescription = new Prescription();
        prescriptionDbRef = FirebaseDatabase.getInstance().getReference().child("Prescriptions");

        btnInsertPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prescription.setDoctorname(editTextDoctorName.getText().toString().trim());
                prescription.setDoctoraddress(editTextDoctorAddress.getText().toString().trim());
                prescription.setPatientname(editTextPatientName.getText().toString().trim());
                prescription.setPhonenumber(editTextPhoneNumber.getText().toString().trim());
                prescription.setAddress(editTextAddress.getText().toString().trim());
                prescription.setAge(editTextAge.getText().toString().trim());
                prescription.setGender(editTextGender.getText().toString().trim());
                prescription.setNote(editTextNote.getText().toString().trim());
                prescription.setSignature(editTextSignature.getText().toString().trim());
                prescription.setDate(editTextDate.getText().toString().trim());
                prescriptionDbRef.push().setValue(prescription);
                Toast.makeText(DoctorPrescription.this, "Prescription completed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}