package com.example.notetoselfapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNewNote extends DialogFragment {

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);
        final EditText editTitle = dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = dialogView.findViewById(R.id.checkBoxImportant);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOK = dialogView.findViewById(R.id.btnOK);


        builder.setView(dialogView).setMessage("Add a new Note");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note newNote = new Note();

                newNote.setmTitle(editTitle.getText().toString());
                newNote.setmDescription(editDescription.getText().toString());
                newNote.setmIdea(checkBoxIdea.isChecked());
                newNote.setmTodo(checkBoxTodo.isChecked());
                newNote.setmImportant(checkBoxImportant.isChecked());
                MainActivity callingActivity = (MainActivity) getActivity();
                callingActivity.createNewNote(newNote);
                //quit the dialog
                dismiss();
            }
        });
        return builder.create();
    }
}
