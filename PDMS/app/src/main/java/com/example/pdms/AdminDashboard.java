package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboard extends AppCompatActivity {
    Button btn_account, btn_bill, btn_logout;
    TextView txt_adminName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btn_account = (Button)findViewById(R.id.buttonADMINUSERS);
        btn_bill = (Button)findViewById(R.id.buttonBILL);
        btn_logout = (Button)findViewById(R.id.buttonLOGOUT);
        txt_adminName = (TextView)findViewById(R.id.txt_adminName);
        setTxtAdminName(txt_adminName);

        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdminSearch = new Intent(AdminDashboard.this, AdminAccountSearch.class);
                startActivity(toAdminSearch);
            }
        });
        btn_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toModFees = new Intent(AdminDashboard.this, AdminModifyFees.class);
                startActivity(toModFees);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent AdmintoLogin = new Intent(AdminDashboard.this, Login.class);
                startActivity(AdmintoLogin);
                finish();
            }
        });
    }
    private void setTxtAdminName(TextView txt_AdminName) {
        DatabaseReference patientRef = FirebaseDatabase.getInstance().getReference("Admins")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                if(snapshot.exists()) {
                    Admin newAdmin = snapshot.getValue(Admin.class);
                    txt_AdminName.setText("Welcome, " + newAdmin.getName());
                }
                //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}