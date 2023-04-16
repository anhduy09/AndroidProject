package com.example.agedatabaseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InsertFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.content_insert, container, false);

        //database and UI code here in next chapter
        final DataManager dm =  new DataManager(getActivity());
        Button btnInsert = view.findViewById(R.id.btnInsert);
        final EditText editname = view.findViewById(R.id.editName);
        final EditText editAge = view.findViewById(R.id.editAge);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.insert(editname.getText().toString(), editAge.getText().toString());
            }
        });

        return view;
    }
}
