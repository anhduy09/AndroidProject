package com.example.agedatabaseapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.content_result, container, false);

        //create an instance of our DataManager
        DataManager dm = new DataManager(getActivity());

        //get a reference to the TextView to show the results
        TextView textResult = view.findViewById(R.id.textResult);

        //create and init a Cursor with all the results
        Cursor c = dm.selectAll();
        //A String to hold all the text
        String list = "";
        //loop through the results in the Cursor
        while (c.moveToNext()) {
            // add the results to the String
            //with a little formatting
            list += (c.getString(1) + " - " + c.getString(2) +"\n");
        }
        //display String in the Textview
        textResult.setText(list);
        return view;
    }
}
