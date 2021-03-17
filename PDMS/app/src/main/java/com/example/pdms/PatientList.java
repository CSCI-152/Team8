package com.example.pdms;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class PatientList extends ArrayAdapter<Patient> {

    private Activity context;
    private List<Patient> patientList;
    DatabaseReference databaseReference;
    EditText etUsername;
    EditText etPassword;


    public PatientList(@NonNull Activity context, List<Patient> patientList, DatabaseReference databaseReference, EditText etUsername, EditText etPassword) {
        super(context, R.layout.layout_patient_list, patientList);
        this.context = context;
        this.patientList = patientList;
        this.databaseReference = databaseReference;
        this.etUsername = etUsername;
        this.etPassword = etPassword;

    }
    public View getView(int pos, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_patient_list, null, true);
        TextView txtUsername = listViewItem.findViewById(R.id.txtUsername);
        TextView txtPassword = listViewItem.findViewById(R.id.txtPassword);
        Button btnDelete = (Button) listViewItem.findViewById(R.id.btnDelete);
        Button btnUpdate = (Button) listViewItem.findViewById(R.id.btnUpdate);

        final Patient patient = patientList.get(pos);
        txtUsername.setText(patient.getUsername());
        txtPassword.setText(patient.getPassword());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(patient.getId()).removeValue();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText(patient.getUsername());
                etPassword.setText(patient.getPassword());
                AdminAccountSearch.patientId = patient.getId();
            }
        });
        return listViewItem;
    }
}
