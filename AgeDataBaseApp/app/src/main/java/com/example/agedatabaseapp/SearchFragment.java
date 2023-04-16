package com.example.agedatabaseapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.content_search, container, false);

        Button btnSearch = view.findViewById(R.id.btnSearch);
        final EditText editSearch =
                view.findViewById(R.id.editSearch);
        final TextView textResult =
                view.findViewById(R.id.textResult);
// This is our DataManager instance
        final DataManager dm =
                new DataManager(getActivity());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c =dm.searchName(editSearch.getText().toString());

                //make sure a result was found before using the cursor
                if(c.getCount() > 0) {
                    c.moveToNext();
                    textResult.setText(("Result = " + c.getString(1) + " - "+
                            c.getString(2)));
                }
            }
        });

        return view;
    }
}
