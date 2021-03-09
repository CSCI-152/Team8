package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import java.util.List;

public class AdminAccountSearch extends AppCompatActivity {
    Button btnAdd;
    EditText etUsername, etPassword;
    DatabaseReference databaseReference;
    ListView listViewPatients;
    List<Patient> patientList;
    public static String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_search);

        patientList = new ArrayList<Patient>();
        databaseReference = FirebaseDatabase.getInstance().getReference("PatientAccounts");

        btnAdd = (Button)findViewById(R.id.btnAdd);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        listViewPatients = (ListView)findViewById(R.id.listViewPatients);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(TextUtils.isEmpty(patientId)) {
                    //add
                    String id = databaseReference.push().getKey();
                    Patient patient = new Patient(id, username, password);
                    databaseReference.child(id).setValue(patient);

                    Toast.makeText(AdminAccountSearch.this, "Patient Account Created: Success", Toast.LENGTH_SHORT).show();

                } else {
                    //update
                    databaseReference.child(patientId).child("username").setValue(username);
                    databaseReference.child(patientId).child("password").setValue(password);
                    Toast.makeText(AdminAccountSearch.this, "Patient Account Updated: Success", Toast.LENGTH_SHORT).show();

                }
                etUsername.setText(null);
                etPassword.setText(null);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Patient patient = postSnapshot.getValue(Patient.class);
                    patientList.add(patient);
                }
                PatientList patientAdapter = new PatientList(AdminAccountSearch.this, patientList, databaseReference, etUsername, etPassword);
                listViewPatients.setAdapter(patientAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}