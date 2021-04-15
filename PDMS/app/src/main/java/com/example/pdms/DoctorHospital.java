package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorHospital extends AppCompatActivity {

    EditText editHospital;
    Button enter_Hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_hospital);

        editHospital = findViewById(R.id.Edit_Hospital);
        enter_Hospital = findViewById(R.id.Enter_Hospital);

        enter_Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hospital = editHospital.getText().toString();

                FirebaseDatabase.getInstance().getReference("Hospitals")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("hospital_1")
                        .setValue(hospital).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DoctorHospital.this, "Register Hospital Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(DoctorHospital.this, "Fail To Register Hospital", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}