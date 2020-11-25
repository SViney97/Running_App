package edu.sviney.running_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    CountUpTimer timer;
    TextView counter, StepCounter;
    Button btnShowRun;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private final int high_Step = 11;
    private  final int low_Step = 8;
    boolean highlimit = false;

    int CounterSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowRun = findViewById(R.id.BtnShowRun);

        counter = findViewById(R.id.TVTimeCounter);


        timer = new CountUpTimer(300000) {
            public void onTick(int second) {
                counter.setText(String.valueOf(second));
                //StepCounter.setText(String.valueOf(CounterSteps));
            }
        };

        StepCounter =findViewById(R.id.TVStepsCounter);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }
    //accelorometer part of assignment
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float X = Math.abs(event.values[0]);
        float Y = Math.abs(event.values[1]);
        float Z = Math.abs(event.values[2]);

        double mag = (Math.sqrt((X*X) +(Y*Y) + (Z*Z)));

        if(mag>high_Step && highlimit ==false){
            highlimit = true;
        }
        if (mag<low_Step && highlimit == true)
        {
            CounterSteps++;
            StepCounter.setText(String.valueOf(CounterSteps));
            highlimit = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void DoStart(View view) {
        timer.start();
        onResume();
        Toast.makeText(this, "Timer has started", Toast.LENGTH_SHORT).show();
        btnShowRun.setEnabled(false);

    }
    public void DoStop(View view) {
        timer.cancel();
        CounterSteps = 0;
        Toast.makeText(this, "Timer has stoped", Toast.LENGTH_SHORT).show();
        btnShowRun.setEnabled(true);
    }
    public void DoReset(View view) {

        counter.setText("0");
        StepCounter.setText("0");
        CounterSteps =0;
        Toast.makeText(this, "Reseted", Toast.LENGTH_SHORT).show();

    }
    public void DoShowRun(View view) {

        Intent ShowRun = new Intent(view.getContext(),ShowRun .class);

        int sec = Integer.valueOf(counter.getText().toString());
        ShowRun.putExtra("time",sec);

        onPause();
        int stepsDone = Integer.valueOf(StepCounter.getText().toString());

        ShowRun.putExtra("steps",stepsDone);
        startActivity(ShowRun);

    }



}