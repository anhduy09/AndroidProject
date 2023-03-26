package com.example.devicedetectionminiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtOrientation;
    private TextView txtResolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOrientation = findViewById(R.id.txtOrientation);
        txtResolution = findViewById(R.id.txtResolution);
    }
    public void DetectDevice(View view){
        Display display = getWindowManager().getDefaultDisplay();
        txtOrientation.setText(""+ display.getRotation());

        //resolution
        Point xy = new Point();
        display.getSize(xy);
        txtResolution.setText("x = "+xy.x + " y = "+xy.y);
    }
}