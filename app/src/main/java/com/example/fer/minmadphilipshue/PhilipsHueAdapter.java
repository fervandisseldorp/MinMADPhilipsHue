package com.example.fer.minmadphilipshue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

import java.util.ArrayList;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class PhilipsHueAdapter extends ArrayAdapter<PhilipsHueLamp> {

    public PhilipsHueAdapter(Context context, ArrayList<PhilipsHueLamp> lamps) {
        super(context, 0, lamps);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        // Recyle view (eventueel)
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row, parent, false);
        }

        // layout components
        TextView lampIdTV = (TextView) convertView.findViewById(R.id.lampIdTV);

        PhilipsHueLamp philipsHueLamp = getItem(position);

        lampIdTV.setText("Lamp "  + philipsHueLamp.getId() );

        // Beetje animeren
        convertView.setAlpha(0.01f);
        convertView.animate().alpha(1.0f).setDuration(2000);

        // return View
        return convertView;

    }

}
