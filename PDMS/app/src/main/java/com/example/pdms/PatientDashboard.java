package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PatientDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btn_calendarandreservation,btn_search;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(PatientDashboard.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);


        btn_calendarandreservation = (Button)findViewById(R.id.buttonCALENDARANDRESERVATION);
        btn_search = (Button)findViewById(R.id.buttonSEARCH);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PatientDashboard.this, "GO TO SEARCH", Toast.LENGTH_SHORT).show();
                Intent patientSearch = new Intent(PatientDashboard.this, PatientSearch.class);
                startActivity(patientSearch);
            }
        });
        btn_calendarandreservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PatientDashboard.this, "RESERVE APPOINTMENT", Toast.LENGTH_SHORT).show();
                Intent patientReservation = new Intent(getBaseContext(), PatientReservation.class);
                LocalDoctor blankDoctor = new LocalDoctor("","");
                patientReservation.putExtra("LocalDoctor", blankDoctor);
                startActivity(patientReservation);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.home){
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.bill){
            Toast.makeText(this, "Bill", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.profile){
            Intent toProfile = new Intent(this, Patient_Profile.class);
            startActivity(toProfile);
        }
        if(id == R.id.setting){
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.contactus){
            Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.aboutus){
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            Intent logOut = new Intent(this, Login.class);
            startActivity(logOut);
        }
        return false;
    }
}