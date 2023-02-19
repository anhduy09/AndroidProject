package com.example.worldcupapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryModelClass> {
    private ArrayList<CountryModelClass> arrayList;
    Context context;
    public CustomAdapter(ArrayList<CountryModelClass> data, Context context) {
        super(context, R.layout.myitem,  data);
        this.arrayList = data;
        this.context = context;
    }

    private static class ViewHolder {
        TextView txtCountry, txtWincup;
        ImageView img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // get the data item for this position
        CountryModelClass dataModel = getItem(position);
        //check if existing view is being used, otherwise inflate the view
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.myitem,
                    parent,
                    false);
            viewHolder.txtCountry = (TextView) convertView.findViewById(R.id.countryName);
            viewHolder.txtWincup = (TextView) convertView.findViewById(R.id.cupWin);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        //get the data from model class
        viewHolder.txtCountry.setText(dataModel.getCountryName());
        viewHolder.txtWincup.setText(dataModel.getCupWin());
        viewHolder.img.setImageResource(dataModel.getImg());
        return convertView;
    }
}
