package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;

public class PatientSettings extends AppCompatActivity {
    SwitchCompat pdmsswitch_1, pdmsswitch_2, carswitch_3, carswitch_4;
    boolean StateSwitch_1, StateSwitch_2, StateSwitch_3, StateSwitch_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);
        final SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);

        pdmsswitch_1 = (SwitchCompat) findViewById(R.id.pdmsswitch_1);
        pdmsswitch_2 = (SwitchCompat) findViewById(R.id.pdmsswitch_2);

        pdmsswitch_1.setChecked(preferences.getBoolean("discount",false));
        pdmsswitch_2.setChecked(preferences.getBoolean("doctor",false));

        pdmsswitch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdmsswitch_1.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("PREFS"
                            , MODE_PRIVATE).edit();
                    editor.putBoolean("discount", true);
                    editor.apply();
                    pdmsswitch_1.setChecked(true);
                }
                else{
                    SharedPreferences.Editor editor = getSharedPreferences("PREFS"
                            , MODE_PRIVATE).edit();
                    editor.putBoolean("discount", false);
                    editor.apply();
                    pdmsswitch_1.setChecked(false);
                }
            }
        });
        pdmsswitch_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdmsswitch_2.isChecked()){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("doctor", true);
                    editor.apply();
                    pdmsswitch_2.setChecked(true);
                }
                else{
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("doctor", false);
                    editor.apply();
                    pdmsswitch_2.setChecked(false);
                }
            }
        });

    }

}