package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner hairSpin;
    private Button random;
    private Face faceView;
    private RadioGroup featureGroup;
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;

    // private variables for all components that need to be edited with controls on bottom

    String[] hair_array = {"Afro", "Mohawk", "Flat Top"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByID for all components that need it
        hairSpin = findViewById(R.id.styleSpinner);
        random = findViewById(R.id.randomFace);
        faceView = findViewById(R.id.faceSurfaceView);
        featureGroup = findViewById(R.id.featureSet);
        redBar = findViewById(R.id.seekRed);
        greenBar = findViewById(R.id.seekGreen);
        blueBar = findViewById(R.id.seekBlue);

        ArrayAdapter <String> hairAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.hair_array); // simple spinner adapter for... the spinner
        hairSpin.setAdapter(hairAdapter);

        FaceController controller = new FaceController(faceView, redBar, greenBar, blueBar); //make new controller class to control all changes

        // set all listeners that are needed for each components
        hairSpin.setOnItemSelectedListener(controller);
        random.setOnClickListener(controller);
        featureGroup.setOnCheckedChangeListener(controller);
        redBar.setOnSeekBarChangeListener(controller);
        greenBar.setOnSeekBarChangeListener(controller);
        blueBar.setOnSeekBarChangeListener(controller);
    }
}