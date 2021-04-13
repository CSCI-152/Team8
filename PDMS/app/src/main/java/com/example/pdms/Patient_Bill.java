package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Patient_Bill extends AppCompatActivity {

    private TextView TotalBill;
    RecyclerView recyclerView;
    private BillAdapter adapter;
    private ArrayList<Fees> list;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    Double totalBill =0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__bill);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Bill");

        RecyclerView recyclerView = findViewById(R.id.Patient_Bill_RecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();
        adapter = new BillAdapter(this,list);
        recyclerView.setAdapter(adapter);

        TotalBill = findViewById(R.id.Total_Bill_Value);
        TotalBill.setText(totalBill.toString());


        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Fees feesList = snapshot.getValue(Fees.class);
                if(feesList.getPatientId()
                        .equals(Objects.requireNonNull
                                (FirebaseAuth.getInstance().getCurrentUser()).getUid()))
                {
                    list.add(feesList);
                    double temp = Double.parseDouble(feesList.getBill());
                    totalBill = totalBill + temp;
                    TotalBill.setText(totalBill.toString());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                Fees feesList = snapshot.getValue(Fees.class);
                assert feesList != null;
                if(feesList.getPatientId()
                        .equals(Objects.requireNonNull
                                (FirebaseAuth.getInstance().getCurrentUser()).getUid()))
                {
                    list.remove(feesList);
                    double temp = Double.parseDouble(feesList.getBill());
                    totalBill = totalBill - temp;
                    TotalBill.setText(totalBill.toString());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}