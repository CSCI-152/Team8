package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class PatientSettings extends AppCompatActivity {
    SwitchCompat pdmsswitch_1, pdmsswitch_2, carswitch_3, carswitch_4;
    boolean StateSwitch_1, StateSwitch_2, StateSwitch_3, StateSwitch_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);
        final SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        StateSwitch_1 = preferences.getBoolean("switch1", false);
        StateSwitch_2 = preferences.getBoolean("switch2", false);

        pdmsswitch_1 = (SwitchCompat) findViewById(R.id.pdmsswitch_1);
        pdmsswitch_2 = (SwitchCompat) findViewById(R.id.pdmsswitch_2);

        pdmsswitch_1.setChecked(StateSwitch_1);
        pdmsswitch_2.setChecked(StateSwitch_2);

        pdmsswitch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateSwitch_1 = !StateSwitch_1;
                pdmsswitch_1.setChecked(StateSwitch_1);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("pdmsswitch_1", StateSwitch_1);
                editor.apply();
            }
        });
        pdmsswitch_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateSwitch_2 = !StateSwitch_2;
                pdmsswitch_2.setChecked(StateSwitch_2);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("pdmsswitch_2", StateSwitch_2);
                editor.apply();
            }
        });

    }
}