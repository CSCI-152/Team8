package com.example.pdms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientReservation_success extends AppCompatActivity {
    TextView txt_reservation;
    Reservation currentReservation;
    Doctor selectedDoctor;
    Button bt_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reservation_success);
        txt_reservation = findViewById(R.id.txt_message_2);
        currentReservation = (Reservation) getIntent().getSerializableExtra("Reservation");
        selectedDoctor = (Doctor) getIntent().getSerializableExtra("Doctor");
        bt_home = findViewById(R.id.bt_home);
        displayReservation();

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void displayReservation() {
        String date = currentReservation.printReservationDateFormatted();
        String doctorName = selectedDoctor.getName();
        String hospitalName = currentReservation.getHospital();
        txt_reservation.setText("Appointment: \n" + doctorName + " | " + hospitalName + " | " + date);
    }
}
