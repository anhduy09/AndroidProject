package com.example.frenchteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    Button yellowbtn, redbtn, greenbtn, purplebtn, blackbtn;
//    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        yellowbtn = findViewById(R.id.yelbtn);
//        redbtn = findViewById(R.id.redbtn);
//        greenbtn = findViewById(R.id.greenbtn);
//        purplebtn = findViewById(R.id.purplebtn);
//        blackbtn = findViewById(R.id.blackbtn);




    }
    public void sayColorInFrench(View view) {
        Button clickedBtn = (Button) view;
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(
                clickedBtn.getTag().toString(), "raw", getPackageName()
        ));
        mediaPlayer.start();
    }

}