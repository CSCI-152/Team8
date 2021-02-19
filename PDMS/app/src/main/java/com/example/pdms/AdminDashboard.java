package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                Toast.makeText(AdminDashboard.this, "GO TO APPOINTMENT", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AdminDashboard.this, "SIGN OUT", Toast.LENGTH_SHORT).show();
            }
        });
    }
}