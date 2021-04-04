package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Patient_Bill extends AppCompatActivity {

    private TextView TotalBill;
    private RecyclerView recyclerView;
    private BillAdapter adapter;
    private ArrayList<Fees> list;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("ModifyFees")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    Double totalBill =0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__bill);

        recyclerView = findViewById(R.id.Patient_Bill_RecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();
        adapter = new BillAdapter(this,list);
        recyclerView.setAdapter(adapter);

        TotalBill = findViewById(R.id.Total_Bill_Value);
        TotalBill.setText(totalBill.toString());

        reference.addValueEventListener(new ValueEventListener() {
            double temp;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Fees feesList = dataSnapshot.getValue(Fees.class);
                    list.add(feesList);
                    temp = Double.parseDouble(feesList.getBill());
                    totalBill = totalBill + temp;
                }
                adapter.notifyDataSetChanged();
                TotalBill.setText(totalBill.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter.setOnItemClickListener(new BillAdapter.OnItemClickListener() {
            @Override
            public void deleteItemClick(int position) {
                list.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

    }
}