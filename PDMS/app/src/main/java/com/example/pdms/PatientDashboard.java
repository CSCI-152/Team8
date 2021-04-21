package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;

public class PatientDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btn_calendarandreservation,btn_search, btn_dosage;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        //get the shared preference we save value in
        FirebaseInAppMessaging.getInstance().setAutomaticDataCollectionEnabled(true);
        //enable inAppMessage to send message
        Boolean isNotification = preferences.getBoolean("discount",false);
        if(isNotification) {//if discount notification is set to true call the notification
            addNotifcationEvent();//log discount notification
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(PatientDashboard.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);


        btn_calendarandreservation = (Button)findViewById(R.id.buttonCALENDARANDRESERVATION);
        btn_search = (Button)findViewById(R.id.buttonSEARCH);
        btn_dosage = (Button)findViewById(R.id.buttonDOSAGE);

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
                Doctor blankDoctor = new Doctor("","");
                patientReservation.putExtra("Doctor", blankDoctor);
                startActivity(patientReservation);

            }
        });

        btn_dosage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PatientDashboard.this, "Click on Dosage", Toast.LENGTH_SHORT).show();
                //Intent patientReservation = new Intent(getBaseContext(), PatientReservation.class);
                //Doctor blankDoctor = new Doctor("","");
                //patientReservation.putExtra("Doctor", blankDoctor);
                //startActivity(patientReservation);
                Intent patientDosage = new Intent(PatientDashboard.this, PatientDosage.class);
                startActivity(patientDosage);

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
            Intent toBill = new Intent(PatientDashboard.this, Patient_Bill.class);
            startActivity(toBill);
        }
        if(id==R.id.reservationlist){
            Intent toReservationList = new Intent(PatientDashboard.this, PatientReservationList.class);
            startActivity(toReservationList);
        }
        if(id == R.id.profile){
            Intent toProfile = new Intent(this, Patient_Profile.class);
            startActivity(toProfile);
        }
        if(id == R.id.setting){
            Intent toSettings = new Intent(this, PatientSettings.class);
            startActivity(toSettings);
        }
        if(id == R.id.contactus) {
            Intent toContactUs = new Intent(this, PatientContactUs.class);
            startActivity(toContactUs);
        }
        if(id == R.id.aboutus){
            Intent toAboutUs = new Intent(this, PatientAboutUs.class);
            startActivity(toAboutUs);

        }
        if(id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            Intent logOut = new Intent(this, Login.class);
            startActivity(logOut);
        }
        return false;
    }

    private void addNotifcationEvent(){
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString("title", "Discount Notification");//set the title
        firebaseAnalytics.logEvent("discount_notification", bundle);//set the event
    }

}