package com.example.videoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView, videoView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        videoView1 = findViewById(R.id.videoView1);
        // 1. from local storage
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.mountains);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);

        // play video from internet URLs )link)
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=9NOlPeaPp9E");
        videoView1.setVideoURI(uri);
        MediaController mc1 = new MediaController(this);
        mc1.setAnchorView(videoView1);
        videoView1.setMediaController(mc1);
        videoView1.start();
    }
}