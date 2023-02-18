package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button sign_in_btn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_in_btn = findViewById(R.id.signin_btn);
//        share_btn = findViewById(R.id.share_number_btn);
        editText = findViewById(R.id.editText);

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                //Explicit intent
                Intent i = new Intent(getApplicationContext(), LuckyNumberActivity.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }
}