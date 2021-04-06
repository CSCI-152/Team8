package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayPrescription extends AppCompatActivity {
    ListView myListView;
    List<Prescription> prescriptionList;
    DatabaseReference prescriptionDbf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_prescription);
        myListView = findViewById(R.id.myListView);
        prescriptionList = new ArrayList<>();

        prescriptionDbf = FirebaseDatabase.getInstance().getReference("Prescriptions");

        prescriptionDbf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prescriptionList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Prescription prescription = dataSnapshot.getValue(Prescription.class);
                    prescriptionList.add(prescription);
                }
                PrescriptionAdapter adapter = new PrescriptionAdapter(DisplayPrescription.this, prescriptionList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}