package com.example.agedatabaseapp;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import com.google.android.material.
        floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Menu;
import android.view.View;
import com.google.android.material.navigation.
        NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "your action is...", Snackbar.LENGTH_LONG).setAction(
                        "Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handle action bar item clicks here. the action bar will
        // automatically handle clicks on the Home/Up button
        // so long as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
        //noinspection simplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here
        // create a transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if(id == R.id.nav_insert) {
            // Create a new fragment of approriate type
            InsertFragment fragment = new InsertFragment();
            transaction.replace(R.id.fragmentHolder, fragment);
        } else if (id == R.id.nav_search) {
            SearchFragment fragment = new SearchFragment();
            transaction.replace(R.id.fragmentHolder, fragment);
        } else if (id == R.id.nav_delete) {
            DeleteFragment fragment = new DeleteFragment();
            transaction.replace(R.id.fragmentHolder, fragment);
        } else if (id == R.id.nav_result) {
            ResultFragment fragment = new ResultFragment();
            transaction.replace(R.id.fragmentHolder, fragment);
        }

        // ask android to remember which menu options the user has chosen
        transaction.addToBackStack(null);

        //implement the change
        transaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
