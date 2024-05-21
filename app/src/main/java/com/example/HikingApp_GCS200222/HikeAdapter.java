package com.example.HikingApp_GCS200222;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myproject.R;

import java.util.List;

public class HikeAdapter extends ArrayAdapter<Hike> {
    public HikeAdapter(Context context, List<Hike> hikes) {
        super(context, 0, hikes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hike hike = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_hike, parent, false);
        }

        TextView hikeNameTextView = convertView.findViewById(R.id.hikeNameTextView);
        TextView hikeLocationTextView = convertView.findViewById(R.id.hikeLocationTextView);
        TextView hikeDateTextView = convertView.findViewById(R.id.hikeDateTextView);
        TextView hikeDistanceTextView = convertView.findViewById(R.id.hikeDistanceTextView);
        TextView difficultyTextView = convertView.findViewById(R.id.difficultyTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);

        hikeNameTextView.setText("Hike Name: " + hike.getHikeName());
        hikeLocationTextView.setText("Location: " + hike.getHikeLocation());
        hikeDateTextView.setText("Date: " + hike.getHikeDate());
        // Đặt các TextView cho các trường dữ liệu bổ sung
        hikeDistanceTextView.setText("Distance (in km): " + hike.getHikeDistance());
        difficultyTextView.setText("Difficulty: " + hike.getDifficulty());
        descriptionTextView.setText("Description: " + hike.getDescription());

        return convertView;
    }
}
