package com.example.worldcupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<CountryModelClass> data;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        //data source
        data = new ArrayList<>();
        data.add(new CountryModelClass("Brazil", "5",R.drawable.brazil));
        data.add(new CountryModelClass("Germany", "4", R.drawable.germany));
        data.add(new CountryModelClass("France", "3", R.drawable.france));
        data.add(new CountryModelClass("Saudi Arabia", "0", R.drawable.saudiarabia));
        data.add(new CountryModelClass("Spain", "2", R.drawable.spain));
        data.add(new CountryModelClass("United Kingdom", "1", R.drawable.unitedkingdom));
        data.add(new CountryModelClass("USA", "0", R.drawable.unitedstates));

        //3. adapter
        customAdapter = new CustomAdapter(data,getApplicationContext());
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this," Country "+customAdapter.getItem(position).getCountryName(), Toast.LENGTH_LONG).show();
            }
        });

    }


}