package com.example.simplefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get a fragment manager
        FragmentManager fManager = getSupportFragmentManager();

        //create a new fragment using the manager
        //passing in the id of the layout to hold it
        Fragment frag = fManager.findFragmentById(R.id.fragmentHolder);

        //check the fragment has not already been initialized
        if(frag == null) {
            //Initialize the fragment based on out simpleFragment
            frag = new SimpleFragment();
            fManager.beginTransaction()
                    .add(R.id.fragmentHolder, frag)
                    .commit();
        }
    }
}