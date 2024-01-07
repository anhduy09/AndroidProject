package com.example.noted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private JSONSerializer mSerializer;
    private static final String TAG =  MainActivity.class.getSimpleName();

    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "My note .... ", Snackbar.LENGTH_LONG)
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

    public void createNewNote(Note newNote) {
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