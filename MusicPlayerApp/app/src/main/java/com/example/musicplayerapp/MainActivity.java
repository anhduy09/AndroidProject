package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button play_btn, pause_btn, fwd_btn, rw_btn;
    TextView time_left_txt, titSong;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();

    double startTime = 0;
    double finalTime = 0;
    int fwdtime = 10000;
    int bkwTime = 10000;
    static int onetimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_btn = findViewById(R.id.play_btn);
        pause_btn = findViewById(R.id.pause_btn);
        fwd_btn = findViewById(R.id.fwd_btn);
        rw_btn = findViewById(R.id.rw_btn);
        time_left_txt = findViewById(R.id.time_left_txt);
        titSong = findViewById(R.id.songTitle);
        seekBar = findViewById(R.id.seekBar);


        //media
        mediaPlayer = MediaPlayer.create(this, R.raw.astronaut);
        seekBar.setClickable(false);
        
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic();
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        fwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = (int) startTime;
                if((temp + fwdtime) <= finalTime) {
                    startTime = startTime + fwdtime;
                    mediaPlayer.seekTo((int) startTime);
                } else {
                    Toast.makeText(MainActivity.this, "can not jumb forward", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = (int) startTime;
                if ((temp - bkwTime) > 0) {
                    startTime = startTime - bkwTime;
                    mediaPlayer.seekTo((int) startTime);
                } else {
                    Toast.makeText(MainActivity.this, "can not jump backward", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void playMusic() {
        mediaPlayer.start();
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if(onetimeOnly == 0)
        {
            seekBar.setMax((int) finalTime);
            onetimeOnly = 1;
        }

//        titSong.setText(mediaPlayer.get);

        time_left_txt.setText(String.format(
                "%d min:%d sec", TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime)-
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))
        ));

        seekBar.setProgress((int) startTime);
        handler.postDelayed(UpdateSongTime, 100);
    }

    //Creating the Runnable
    private  Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            time_left_txt.setText(String.format(
                    "%d min %d sec", TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)-
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))
            ));
            seekBar.setProgress((int) startTime);
            handler.postDelayed(this,100);
        }
    };
}