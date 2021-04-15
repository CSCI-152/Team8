package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {
    Button btn_account, btn_bill, btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btn_account = (Button)findViewById(R.id.buttonADMINUSERS);
        btn_bill = (Button)findViewById(R.id.buttonBILL);
        btn_logout = (Button)findViewById(R.id.buttonLOGOUT);

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
}