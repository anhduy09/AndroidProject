package com.example.notetoselfapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    Note tempNote = new Note();
//    private List<Note> noteList = new ArrayList<>();
    private JSONSerializer jsonSerializer;
    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    FloatingActionButton fab;
    private boolean mShowDividers;
    private SharedPreferences mPrefs;
//    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogShowNote dialog = new DialogShowNote();
//                dialog.sendNoteSelected(tempNote);
//                dialog.show(getSupportFragmentManager(),"123");
//            }
//        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Replace with your own action",
//                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
                DialogNewNote dialog = new DialogNewNote();
                dialog.show(getSupportFragmentManager(),"");
            }
        });

        jsonSerializer = new JSONSerializer("NoteToSelf.json", getApplicationContext());

        try {
            noteList = jsonSerializer.load();

        } catch (Exception e) {
            noteList = new ArrayList<Note>();
            Log.e("Error loading note: ","",e);
        }

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(this, noteList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    public void saveNotes() {
        try {
            jsonSerializer.save(noteList);

        } catch (Exception e) {
            Log.e("Error saving note...","",e);
        }
    }

    public void createNewNote(Note newNote) {
//        tempNote = newNote;
        noteList.add(newNote);
        mAdapter.notifyDataSetChanged();
    }

    public void showNote(int adapterPosition) {
        DialogShowNote dialogShowNote = new DialogShowNote();
        dialogShowNote.sendNoteSelected(noteList.get(adapterPosition));
        dialogShowNote.show(getSupportFragmentManager(),"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrefs = getSharedPreferences("Note to Self", MODE_PRIVATE);
        mShowDividers = mPrefs.getBoolean("dividers", true);
        if (mShowDividers) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); // add dividing line between items
        } else {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecorationAt(0);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}