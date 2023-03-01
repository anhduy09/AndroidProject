package com.example.notetoselfapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    Note tempNote = new Note();
    private List<Note> noteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    FloatingActionButton fab;
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

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(this, noteList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); // add dividing line between items
        recyclerView.setAdapter(mAdapter);

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
}