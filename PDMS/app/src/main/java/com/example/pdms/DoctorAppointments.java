package com.example.pdms;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    Spinner spn_sortby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);
        listView = findViewById(R.id.listView);
        spn_sortby = findViewById(R.id.spn_sortBy);
        String[] items = new String[]{"","Name", "Date", "Hospital"};
        ArrayAdapter<String> adapterSPN = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spn_sortby.setAdapter(adapterSPN);

        final PrintReservationInfo reservationPrint = new PrintReservationInfo(this);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayList<Reservation> reservationList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(DoctorAppointments.this, R.layout.list_item, list);
        listView.setAdapter(adapter);
        list.add("You have no upcoming appointments");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("DoctorAppointment");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                reservationList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Reservation newReservation = snapshot.getValue(Reservation.class);
                    if(newReservation.getDoctorID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        reservationPrint.displayReservationInfo(newReservation, list, adapter);
                        reservationList.add(newReservation);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        spn_sortby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        sortByName(list,reservationList,adapter);
                        break;
                    case 2:
                        sortByDate(list,reservationList,adapter);
                        break;
                    case 3:
                        sortByHospital(list,reservationList,adapter);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void sortByName(ArrayList<String> list, ArrayList<Reservation> reservationList, ArrayAdapter adapter) {
        if (list.size() == 1 || list.size() == 0) {
            return;
        }
        int size = reservationList.size();
        for(int i = 0; i < size-1; i++) {
            for (int j = i+1; j < reservationList.size(); j++) {
                if(list.get(i).split(" | ")[0].compareTo(list.get(j).split(" | ")[0])>0){
                    swapR(reservationList,i,j);
                    swapL(list,i,j);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void sortByDate(ArrayList<String> list, ArrayList<Reservation> reservationList, ArrayAdapter adapter) {
        if (list.size() == 1 || list.size() == 0) {
            return;
        }
        int size = reservationList.size();
        for(int i = 0; i < size-1; i++) {
            for (int j = i+1; j < reservationList.size(); j++) {
                if(reservationList.get(i).reservationAsDateTime().compareTo(reservationList.get(j).reservationAsDateTime())>0){
                    swapR(reservationList,i,j);
                    swapL(list,i,j);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    void sortByHospital(ArrayList<String> list, ArrayList<Reservation> reservationList, ArrayAdapter adapter) {
        if (list.size() == 1 || list.size() == 0) {
            return;
        }
        int size = reservationList.size();
        for(int i = 0; i < size-1; i++) {
            for (int j = i+1; j < reservationList.size(); j++) {
                if(reservationList.get(i).getHospital().compareTo(reservationList.get(j).getHospital())>0){
                    swapR(reservationList,i,j);
                    swapL(list,i,j);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    void swapR(ArrayList<Reservation> reservationList, int i, int j) {
        Reservation temp = reservationList.get(i);
        reservationList.set(i,reservationList.get(j));
        reservationList.set(j,temp);
    }
    void swapL(ArrayList<String> list, int i, int j) {
        String temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }
    /*
    int partition(ArrayList<Reservation> reservationList, int low, int high) {
        Reservation pivot = reservationList.get(high);
        int i = (low - 1);
        for(int j = low; j <= high - 1; j++) {

        }
    }*/
}