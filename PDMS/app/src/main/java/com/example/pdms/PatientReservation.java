package com.example.pdms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PatientReservation extends AppCompatActivity {
    TextView txt_doctorEmail, txt_hospitalName, txt_selectedDate;
    Button bt_selectDoctor, bt_selectHospital, bt_selectDate, bt_confirm, bt_cancel;
    LocalDoctor selectedDoctor;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    //popup_patient_select_hospital
    ListView pp_listView;
    Button pp_bt_hospital_cancel;
    //popup_patient_select_date
    CalendarView pp_cv_calendarView;
    TimePicker pp_tp_timePicker;
    Button pp_bt_date_confirm, pp_bt_date_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reservation);
        txt_doctorEmail = findViewById(R.id.txt_doctorEmail);
        selectedDoctor = (LocalDoctor) getIntent().getSerializableExtra("LocalDoctor");
        txt_doctorEmail.setText(selectedDoctor.getEmail());
        txt_hospitalName = findViewById(R.id.txt_hospitalName);
        bt_selectDoctor = findViewById(R.id.bt_selectDoctor);
        if(!txt_doctorEmail.getText().toString().isEmpty()) {
            bt_selectDoctor.setText("Change Doctor");
        }
        bt_selectHospital = findViewById(R.id.bt_selectHospital);
        txt_selectedDate = findViewById(R.id.txt_selectedDate);
        bt_selectDate = findViewById(R.id.bt_selectDate);
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_selectDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent patientSearch = new Intent(PatientReservation.this, PatientSearch.class);
                startActivity(patientSearch);
                finish();
            }
        });
        bt_selectHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txt_doctorEmail.getText().toString().isEmpty()) {
                    popupPatientSelectHospital(selectedDoctor);
                }
            }
        });
        bt_selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txt_doctorEmail.getText().toString().isEmpty()) {
                    popupPatientSelectDate(selectedDoctor);
                }
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void popupPatientSelectHospital(LocalDoctor selectedDoctor) {
        //pull list of hospitals correlating to doctor id
        //make hospital class
        //display as listview
        dialogBuilder = new AlertDialog.Builder(PatientReservation.this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_patient_select_hospital, null);
        pp_listView = (ListView) contactPopupView.findViewById(R.id.listView);
        pp_bt_hospital_cancel = (Button) contactPopupView.findViewById(R.id.bt_hospital_cancel);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(PatientReservation.this, R.layout.list_item, list);
        pp_listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Hospitals").child(selectedDoctor.getUID());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String insert = snapshot.getValue().toString();
                    if(!insert.isEmpty()) {
                        list.add(insert);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        pp_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txt_hospitalName.setText(pp_listView.getItemAtPosition(i).toString());
                if(!txt_hospitalName.getText().toString().isEmpty()) {
                    bt_selectHospital.setText("Change Hospital");
                }
                dialog.dismiss();
            }
        });

        pp_bt_hospital_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void popupPatientSelectDate(LocalDoctor selectedDoctor) {
        dialogBuilder = new AlertDialog.Builder(PatientReservation.this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_patient_select_date, null);
        pp_cv_calendarView = (CalendarView) contactPopupView.findViewById(R.id.cv_calendarView);
        pp_tp_timePicker = (TimePicker) contactPopupView.findViewById(R.id.tp_timePicker);
        pp_bt_date_confirm = (Button) contactPopupView.findViewById(R.id.bt_date_confirm);
        pp_bt_date_cancel = (Button) contactPopupView.findViewById(R.id.bt_date_cancel);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        pp_bt_date_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set text to new date
                updateSelectedTime();
                if(!txt_selectedDate.getText().toString().isEmpty()) {
                    bt_selectDate.setText("Change Date");
                }
                dialog.dismiss();
            }
        });
        pp_bt_date_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void updateSelectedTime() {
        //txt_selectedDate.setText("Date test");
    }
}