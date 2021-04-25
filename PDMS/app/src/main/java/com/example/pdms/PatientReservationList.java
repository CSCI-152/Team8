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

public class PatientReservationList extends AppCompatActivity {

    RecyclerView AcceptedView, PendingView;
    FirebaseDatabase database;
    DatabaseReference AcceptedReference, PendingReference;
    ArrayList<Reservation> AcceptedList, PendingList;
    patientReservationAdapter AcceptedAdapter, PendingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientreservationlist);

        database = FirebaseDatabase.getInstance();
        AcceptedReference = database.getReference("DoctorAppointment");
        PendingReference = database.getReference("Reservations");

        AcceptedView = findViewById(R.id.AcceptedReservation_View);
        AcceptedView.setHasFixedSize(true);
        AcceptedView.setLayoutManager(new LinearLayoutManager(this));

        PendingView = findViewById(R.id.PendingReservation_View);
        PendingView.setHasFixedSize(true);
        PendingView.setLayoutManager(new LinearLayoutManager(this));

        AcceptedList = new ArrayList<>();
        AcceptedAdapter = new patientReservationAdapter(this, AcceptedList);
        AcceptedView.setAdapter(AcceptedAdapter);

        PendingList = new ArrayList<>();
        PendingAdapter = new patientReservationAdapter(this, PendingList);
        PendingView.setAdapter(PendingAdapter);

        AcceptedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AcceptedList.clear();
                for(DataSnapshot db : snapshot.getChildren()){
                    Reservation reserve = db.getValue(Reservation.class);
                    if(reserve.getPatientID().equals(FirebaseAuth.getInstance().getCurrentUser()
                            .getUid()))
                    {
                        AcceptedList.add(reserve);
                    }
                    AcceptedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        PendingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PendingList.clear();
                for(DataSnapshot db : snapshot.getChildren()){
                    Reservation reserve = db.getValue(Reservation.class);
                    if(reserve.getPatientID().equals(FirebaseAuth.getInstance().getCurrentUser()
                            .getUid()))
                    {
                        PendingList.add(reserve);
                    }
                    PendingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}