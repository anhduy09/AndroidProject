package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    Button share_btn, back_btn;
    TextView lucky_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);
        share_btn = findViewById(R.id.share_number_btn);
        lucky_text = findViewById(R.id.lucky_num_txtView);
        back_btn = findViewById(R.id.back_btn);

        String userName = getIntent().getStringExtra("name");
        Toast.makeText(this, ""+userName, Toast.LENGTH_LONG);
        int lucky_num = randomNumber();
        lucky_text.setText(Integer.toString(lucky_num));

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,lucky_num);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public static int randomNumber() {
        Random ran =  new Random();
        int upper_limit = 1000;
        int ranNum = ran.nextInt(upper_limit);
        return  ranNum;
    }

    public void  shareData(String name, int luckyNum) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, name);
        i.putExtra(Intent.EXTRA_TEXT, luckyNum);

        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}