package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Patient_Chart extends AppCompatActivity {

    RecyclerView view;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Reservation> list;
    patientChartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__chart);

        view = findViewById(R.id.patient_chart_view);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Reservations");

        list = new ArrayList<>();
        adapter = new patientChartAdapter(this, list);

        view.setAdapter(adapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot db: snapshot.getChildren())
                {
                    Reservation reserve = db.getValue(Reservation.class);
                    if(reserve.getDoctorID().equals(FirebaseAuth.getInstance().getCurrentUser()
                    .getUid()))
                    {
                        list.add(reserve);
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