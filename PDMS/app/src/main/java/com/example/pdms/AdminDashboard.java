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
                //Toast.makeText(AdminDashboard.this, "GO TO APPOINTMENT", Toast.LENGTH_SHORT).show();
                Intent toAdminSearch = new Intent(AdminDashboard.this, AdminAccountSearch.class);
                startActivity(toAdminSearch);
            }
        });
        btn_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "MODIFY BILL", Toast.LENGTH_SHORT).show();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AdminDashboard.this, "SIGN OUT", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent AdmintoLogin = new Intent(AdminDashboard.this, Login.class);
                startActivity(AdmintoLogin);
                finish();
            }
        });
    }
}