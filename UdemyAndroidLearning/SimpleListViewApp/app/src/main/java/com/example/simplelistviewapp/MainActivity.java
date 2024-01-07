package com.example.simplelistviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //1. Adapter view
    ListView listView;
    //2. data source
    String[] wc22 = {
            "Germany",
            "USA",
            "Argentina",
            "Russian",
            "France",
            "England"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        //3. create Adapter
        //3.1 pre adapter
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
//                wc22);
        //3.2 customized adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.myitem,
                R.id.textView, wc22);
        listView.setAdapter(arrayAdapter);
    }
}