package com.example.pdms;

import android.content.Context;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import androidx.annotation.NonNull;

public class PrintReservationInfo {
    private Context ThisContext;

    public PrintReservationInfo(Context c) {
        this.ThisContext = c;
    }
    // context is for testing purposes
    // Toast.makeText(getThisContext(), "test", Toast.LENGTH_SHORT).show();
    public Context getThisContext() {
        return ThisContext;
    }
    public void setThisContext(Context ThisContext) {
        this.ThisContext = ThisContext;
    }

    public String printDate(Reservation thisReservation) {
        //returns date as dd/mm/yyyy, hh:mm
        String initYear = thisReservation.getReservationDate();
        String returnDate = initYear.substring(5, 7) + "/" + // mm
                initYear.substring(8, 10) + "/" +            // dd
                initYear.substring(0, 4) + ", " +            // yyyy
                thisReservation.getReservationHM();         // hh:mm
        return returnDate;
    }
    public void displayReservationInfo(Reservation thisReservation, ArrayList<String> list, ArrayAdapter adapter) {
        String patientID = thisReservation.getPatientID();
        String date = printDate(thisReservation);
        String hospitalName = thisReservation.getHospital();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Patients").child(patientID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PatientUser pu = dataSnapshot.getValue(PatientUser.class);
                list.add(pu.getName() + " | " + hospitalName + " | " + date);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}