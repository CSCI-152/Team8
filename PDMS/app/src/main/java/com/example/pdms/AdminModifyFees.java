package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminModifyFees extends AppCompatActivity {
    EditText editTextBill;
    Button btn_add;
    Spinner spinnerLevels;
    DatabaseReference databaseReference;
    ListView listViewFees;
    List<Fees> feesList;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_modify_fees);
            editTextBill = (EditText) findViewById(R.id.editTextBill);
            btn_add = (Button) findViewById(R.id.btn_add);
            spinnerLevels = (Spinner) findViewById(R.id.spinnerLevels);
            listViewFees = (ListView) findViewById(R.id.listViewFees);
            feesList = new ArrayList<>();
            databaseReference = FirebaseDatabase.getInstance().getReference("ModifyFees");
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addFees();
                }
            });

            listViewFees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Fees fees = feesList.get(position);
                    showUpdateDialog(fees.getId(), fees.getBill());

                    return false;
                }
            });
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    feesList.clear();
                    for (DataSnapshot feesSnapshot : dataSnapshot.getChildren()) {
                        Fees fees = feesSnapshot.getValue(Fees.class);
                        feesList.add(fees);
                    }
                    FeesList adapter = new FeesList(AdminModifyFees.this, feesList);
                    listViewFees.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    feesList.clear();


                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Fees fees = postSnapshot.getValue(Fees.class);

                        feesList.add(fees);
                    }


                    FeesList artistAdapter = new FeesList(AdminModifyFees.this, feesList);

                    listViewFees.setAdapter(artistAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        private void showUpdateDialog(String id, String bill) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

            LayoutInflater inflater = getLayoutInflater();

            final View dialogView = inflater.inflate(R.layout.update_dialog, null);

            dialogBuilder.setView(dialogView);

            final EditText editTextBill = (EditText) dialogView.findViewById(R.id.editTextBill);
            final Button btn_update = (Button) dialogView.findViewById(R.id.btn_update);
            final Spinner spinnerLevels = (Spinner) dialogView.findViewById(R.id.spinnerLevels);
            final Button btn_delete = (Button) dialogView.findViewById(R.id.btn_delete);

            dialogBuilder.setTitle("Updating fees" + bill);

            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String bill = editTextBill.getText().toString().trim();
                    String level = spinnerLevels.getSelectedItem().toString();

                    if(TextUtils.isEmpty(bill)) {
                        editTextBill.setError("Bill required");
                        return;
                    }

                    updateFees(id, level, bill);

                    alertDialog.dismiss();

                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteFees(id);
                }
            });

        }
        private boolean updateFees(String id, String bill, String level) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ModifyFees").child(id);

            Fees fees = new Fees(id, bill, level);

            databaseReference.setValue(fees);

            Toast.makeText(this, "Fees updated", Toast.LENGTH_LONG).show();

            return true;
        }


        private void addFees() {
            String level = spinnerLevels.getSelectedItem().toString();
            String bill = editTextBill.getText().toString().trim();
            if (!TextUtils.isEmpty(bill)) {
                String id = databaseReference.push().getKey();
                Fees fees = new Fees(id, level, bill);
                databaseReference.child(id).setValue(fees);
                Toast.makeText(this, "Fees added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "You should enter missing info", Toast.LENGTH_LONG).show();
            }

        }
        private void deleteFees(String id) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ModifyFees").child(id);
            databaseReference.removeValue();
            Toast.makeText(this, "Fees canceled", Toast.LENGTH_LONG).show();
        }



    }