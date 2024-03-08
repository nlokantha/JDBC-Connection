package com.example.jdbc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DataAdapter extends ArrayAdapter<UserData> {

    public DataAdapter(@NonNull Context context, int resource, @NonNull List<UserData> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.data_row_item, parent, false);
        }
        UserData data = getItem(position);
        TextView textViewUserName = convertView.findViewById(R.id.textViewUserName);
        TextView textViewPassword = convertView.findViewById(R.id.textViewPassword);

        textViewUserName.setText(data.getUserName());
        textViewPassword.setText(data.getPassword());



        return convertView;
    }
}
