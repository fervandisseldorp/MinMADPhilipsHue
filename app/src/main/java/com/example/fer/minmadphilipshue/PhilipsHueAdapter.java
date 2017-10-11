package com.example.fer.minmadphilipshue;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

import java.util.ArrayList;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class PhilipsHueAdapter extends ArrayAdapter<PhilipsHueLamp> {

    public PhilipsHueAdapter(Context context, ArrayList<PhilipsHueLamp> persons) {
        super(context, 0, persons);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Recyle view (eventueel)

        // Get ref van alle UI elementen

        // Person object met alle data

        // Koppelen

        // Beetje animeren


        // return View
        return convertView;

    }

}
