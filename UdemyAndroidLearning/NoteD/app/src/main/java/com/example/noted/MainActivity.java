package com.example.noted;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noted.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private JSONSerializer mSerializer;
    private static final String TAG =  MainActivity.class.getSimpleName();

    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "My note .... ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //create a new DialogNewNote called dialog
                DialogNewNote dialog = new DialogNewNote();

                //send the note via senNoteSelected method
//                dialog.sendNoteSelected(mTempNote);

                //create the dialog
                dialog.show(getSupportFragmentManager(), "123");
            }
        });

        mSerializer = new JSONSerializer("NoteD.json", getApplicationContext());

        try {
            noteList = mSerializer.load();
        } catch (Exception e) {
            noteList = new ArrayList<Note>();
            Log.e(TAG, "Error while loading.....",e);
        }

        //init and setup for recyclerView obj
        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(this, noteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // add a neet dividing line between items in the list
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //set the adapter
        recyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.
                SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move ", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(getApplicationContext(), "onSwiped....", Toast.LENGTH_SHORT).show();
                int pos = viewHolder.getAdapterPosition();
                noteList.remove(pos);
                mAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void createNewNote(Note newNote) {

        //mTempNote = newNote;
        noteList.add(newNote);
        // notifyDataSetChanged() will let adapter know that a new note has been added
        mAdapter.notifyDataSetChanged();
        Log.d(TAG, "createNewNote is called");
    }

    public void showNote(int adapterPosition) {
        DialogNewNote dialog = new DialogNewNote();
        dialog.sendNoteSelected(noteList.get(adapterPosition));
        dialog.show(getSupportFragmentManager(),"");
        Log.d(TAG, "showNote is called");
    }

    public void saveNotes() {
        try {
            mSerializer.save(noteList);
        } catch (Exception e) {
            Log.e(TAG, "Error while saving....", e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNotes();
    }
}