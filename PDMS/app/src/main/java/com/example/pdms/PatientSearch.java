package com.example.pdms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PatientSearch extends AppCompatActivity {
    EditText et_search;
    Button bt_search;
    ListView listView;
    ArrayList<Doctor> doctorList = new ArrayList<Doctor>();

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    TextView pp_txt_doctorName;
    Button pp_bt_makeReservation, pp_bt_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);
        et_search = findViewById(R.id.et_search);
        bt_search = findViewById(R.id.bt_search);
        listView = findViewById(R.id.listView);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(PatientSearch.this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor ld = snapshot.getValue(Doctor.class);
                    list.add(ld.getName());
                    doctorList.add(ld);
                    i++;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorQuery = et_search.getText().toString();
                int doctorPos = doctorListPos(doctorQuery);
                if (doctorPos > -1) {
                    displaySingleEntry(doctorPos);
                } else {
                    Toast.makeText(PatientSearch.this, "0 results", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i == item position
                reservationPopUp(doctorList.get(i));
            }
        });
    }
    private int doctorListPos(String doctorQuery) {
        //search list of doctors
            if (doctorList == null) {
                return -1;
            }
            int length = doctorList.size();
            int j = 0;
            while (j < length) {
                if (doctorList.get(j).getName().equals(doctorQuery)) {
                    return j;
                }
                j = j + 1;
            }
            return -1;
    }
    private void displaySingleEntry(int doctorPos) {
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(PatientSearch.this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        list.clear();
        list.add(doctorList.get(doctorPos).getEmail());
        adapter.notifyDataSetChanged();
    }
    private void reservationPopUp(Doctor selectedDoctor) {
        dialogBuilder = new AlertDialog.Builder(PatientSearch.this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_patient_search, null);
        pp_txt_doctorName = (TextView) contactPopupView.findViewById(R.id.txt_doctorName);
        pp_txt_doctorName.setText(selectedDoctor.getName() + "\n" + selectedDoctor.getEmail());
        pp_bt_makeReservation = (Button) contactPopupView.findViewById(R.id.bt_makeReservation);
        pp_bt_return = (Button) contactPopupView.findViewById(R.id.bt_return);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        pp_bt_makeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(PatientSearch.this, "start reservation activity", Toast.LENGTH_SHORT).show();
                Intent patientReservation = new Intent(getBaseContext(), PatientReservation.class);
                patientReservation.putExtra("Doctor", selectedDoctor);
                startActivity(patientReservation);
                dialog.dismiss();
            }
        });
        pp_bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
