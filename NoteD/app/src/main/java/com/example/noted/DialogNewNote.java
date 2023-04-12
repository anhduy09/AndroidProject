package com.example.noted;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNewNote extends DialogFragment {

    private Note mNote;
    private EditText editTitle;
    private EditText editCategory;
    private EditText editDescription;
//    private boolean isUpdate;

    private Button btnOk;
    private Button btnCancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {     //likely setContentView
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //init LayoutInflater object which inflate XML layout
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //by "inflate", we are simply referring to how we turn our XML layout into a Java object.
        //Once this has been done, we can then access all our widgets in the usual way
        //We can think of the inflate.inflate() method as replacing the setContentView() method for our dialog
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        editTitle = dialogView.findViewById(R.id.title_editText);
        editCategory = dialogView.findViewById(R.id.category_editText);
        editDescription = dialogView.findViewById(R.id.description_editText);
//        isUpdate = false;

        btnOk = dialogView.findViewById(R.id.ok_button);
        btnCancel = dialogView.findViewById(R.id.cancel_button);

        //set a message of dialog using builder which is out builder instance
        builder.setView(dialogView).setMessage("Add a new note");

        //check mNote is empty or not then, if yes, display content of mNote
        if(mNote != null) {
            editTitle.setText(mNote.getmTitle());
            editCategory.setText(mNote.getmCategory());
            editDescription.setText(mNote.getmDescription());
        }


        //Handle the cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss(); //for closing the dialog
            }
        });

//        editTitle.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                editTitle.setText(s);
//                isUpdate = true;
//            }
//        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note newNote = new Note(editTitle.getText().toString(),
                        editCategory.getText().toString(),
                        editDescription.getText().toString());

                //get a reference to MainActivity
                MainActivity callingMainActivity = (MainActivity) getActivity();
                //pass newNote obj to MainActivity
                callingMainActivity.createNewNote(newNote);
                //quit the dialog
                dismiss();
            }
        });
        return builder.create();
    }

    //receive a note from MainActivity
    public void sendNoteSelected(Note noteSelected) {

        mNote = noteSelected;

    }
}
