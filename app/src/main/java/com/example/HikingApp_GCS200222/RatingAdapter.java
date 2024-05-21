package com.example.HikingApp_GCS200222;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myproject.R;

import java.util.List;

public class RatingAdapter extends ArrayAdapter<RatingLevel> {
    public RatingAdapter(Context context, List<RatingLevel> ratingLevels) {
        super(context, 0, ratingLevels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rating_item, parent, false);
        }

        RatingLevel ratingLevel = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.ratingName);
        TextView descriptionTextView = convertView.findViewById(R.id.ratingDescription);

        nameTextView.setText(ratingLevel.getName());
        descriptionTextView.setText(ratingLevel.getDescription());

        return convertView;
    }
}
