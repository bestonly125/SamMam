package com.infosec.mysammap.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.infosec.mysammap.R;

import java.util.List;

public class MyItemAdapter extends ArrayAdapter {
    public MyItemAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootview = convertView;


        if(rootview == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootview = inflater.inflate(R.layout.bottom_sheet,parent,false);
        }

        TextView txtTitile = rootview.findViewById(R.id.title_name);

        MyItem myItem = (MyItem) getItem(position);
        txtTitile.setText(myItem.getTitle());

        return rootview;
    }
}
