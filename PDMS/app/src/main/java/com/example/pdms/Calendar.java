package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.widget.CalendarView;
import android.widget.TextView;
import android.os.Bundle;

public class Calendar extends AppCompatActivity {
    CalendarView calendarView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendar);
        textView = findViewById(R.id.textview);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth+"/"+month+"/"+year;
                textView.setText(date);
            }
        });
    }
}