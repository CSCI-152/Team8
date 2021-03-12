package com.example.pdms;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    ArrayList<LocalDoctor> doctorList = new ArrayList<LocalDoctor>();

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
                    readDoctorData(snapshot.getKey(), snapshot.getValue().toString());
                    list.add(doctorList.get(i).getEmail());
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
    }
    private void readDoctorData(String docKey, String docData) {
        LocalDoctor doctor = new LocalDoctor(docKey, docData.substring(7, docData.length()-1));
        doctorList.add(doctor);
    }
    private int doctorListPos(String doctorQuery) {
        //search list of doctors
            if (doctorList == null) {
                return -1;
            }
            int length = doctorList.size();
            int j = 0;
            while (j < length) {
                if (doctorList.get(j).getEmail().equals(doctorQuery)) {
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
}
