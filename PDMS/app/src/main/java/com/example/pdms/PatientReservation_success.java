package com.example.pdms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PatientReservation_success extends AppCompatActivity {
    TextView txt_reservation;
    Reservation currentReservation;
    Button bt_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reservation_success);
        txt_reservation = findViewById(R.id.txt_message_2);
        currentReservation = (Reservation) getIntent().getSerializableExtra("Reservation");
        bt_home = findViewById(R.id.bt_home);
        displayReservation(currentReservation);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void displayReservation(Reservation currentReservation) {
        String date = currentReservation.printReservationDateFormatted();
        String doctorID = currentReservation.getDoctorID();
        String hospitalName = currentReservation.getHospital();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Doctors").child(doctorID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    txt_reservation.setText("Appointment: \n" + snapshot.getValue().toString() + " | " + hospitalName + " | " + date);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txt_reservation.setText("error");
            }
        });
    }
}
