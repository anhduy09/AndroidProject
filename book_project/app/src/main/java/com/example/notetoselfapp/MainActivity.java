package com.example.notetoselfapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Note tempNote = new Note();
    FloatingActionButton fab;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        fab = findViewById(R.id.fab);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogShowNote dialog = new DialogShowNote();
                dialog.sendNoteSelected(tempNote);
                dialog.show(getSupportFragmentManager(),"123");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Replace with your own action",
//                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
                DialogNewNote dialog = new DialogNewNote();
                dialog.show(getSupportFragmentManager(),"");
            }
        });


    }

    public void createNewNote(Note newNote) {
        tempNote = newNote;
    }
}