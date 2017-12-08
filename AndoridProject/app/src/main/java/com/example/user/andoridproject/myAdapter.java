package com.example.user.andoridproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by study on 4/12/17.
 */

public class myAdapter extends ArrayAdapter<RouteType> {
    TextView temp;
    public myAdapter(Context context, ArrayList<RouteType> routes) {
        super(context, 0, routes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.myadapter, parent, false);
        }

        RouteType p = getItem(position);

        temp =v.findViewById(R.id.oneRoute);

        temp.setText(p.getString(p.place, p.mode));

        return v;

    }
}
