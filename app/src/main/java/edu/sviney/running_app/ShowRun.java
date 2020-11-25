package edu.sviney.running_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.Locale;

public class ShowRun extends AppCompatActivity {

    int Time,Step;
    double NumMeterRan,RunCalories;
    TextView TimeDisplay, DateDisplay,MeterRanDisplay,CaloriesDisplay;
    Date CurrentDate = Calendar.getInstance().getTime();
    SimpleDateFormat dateFormat;
    String Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_run);

        TimeDisplay = findViewById(R.id.TVTimeDisplay);
        DateDisplay = findViewById(R.id.TVDateDisplay);
        MeterRanDisplay = findViewById(R.id.TVMeterRanDisplay);
        CaloriesDisplay = findViewById(R.id.TVCaloriesDisplay);


        Time = getIntent().getIntExtra("time",0);
        Step = getIntent().getIntExtra("steps",0);
        TimeDisplay.setText(String.valueOf(Time));

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date = dateFormat.format(CurrentDate);
        DateDisplay.setText(Date);

        NumMeterRan =(double)Step *0.8;
        MeterRanDisplay.setText(String.format("%.2g%n",NumMeterRan));

        RunCalories = (double) Step *0.04;
        CaloriesDisplay.setText(String.format("%.2g%n",RunCalories));


    }

    public void DoBack(View view) {

        finish();
    }
}