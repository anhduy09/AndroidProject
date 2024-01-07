package com.example.imagepaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter adapter;

    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //grab all the images and stuff em in our array
        images = new int[] {
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,
                R.drawable.pic5,
                R.drawable.pic6
        };

        //get a reference to the ViewPager in the layout
        viewPager = findViewById(R.id.paper);

        // init our adaper

        adapter = new ImagePaperAdapter(MainActivity.this, images);

        //bind the adapter to the ViewPager
        viewPager.setAdapter(adapter);
    }
}