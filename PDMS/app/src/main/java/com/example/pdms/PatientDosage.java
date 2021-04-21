package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientDosage extends AppCompatActivity {
    RecyclerView view;
    UserDB name;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Prescription> list;
    patientDosageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dosage);

        view = findViewById(R.id.patient_dosage_view);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Prescriptions");
        DatabaseReference name = FirebaseDatabase.getInstance().getReference("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        //DatabaseReference patients1 = FirebaseDatabase.getInstance().getReference("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        list = new ArrayList<>();
        adapter = new patientDosageAdapter(this, list);

        view.setAdapter(adapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot db: snapshot.getChildren())
                {
                    Prescription prescription = db.getValue(Prescription.class);
                    if(prescription.getPatientname().equals(name))
                    {
                        list.add(prescription);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
