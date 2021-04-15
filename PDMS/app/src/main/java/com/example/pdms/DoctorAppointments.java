package com.example.pdms;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorAppointments extends AppCompatActivity {
    ListView listView;
    View calendarView;
    Button bt_switch_cv, bt_switch_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper);
        listView = findViewById(R.id.listView);
        calendarView = findViewById(R.id.calendarView);
        bt_switch_cv = findViewById(R.id.bt_switch_cv);
        bt_switch_lv = findViewById(R.id.bt_switch_lv);

        final PrintReservationInfo reservationPrint = new PrintReservationInfo(this);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(DoctorAppointments.this, R.layout.list_item, list);
        listView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Reservations");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Reservation newReservation = snapshot.getValue(Reservation.class);
                    if(newReservation.getDoctorID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                         reservationPrint.retrievePatientName(newReservation, list, adapter);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        bt_switch_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
        bt_switch_lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });
    }
}
