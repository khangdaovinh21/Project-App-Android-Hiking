package com.example.HikingApp_GCS200222;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;

import java.util.List;

public class AdapterHike extends BaseAdapter {
    private Context context;
    private List<ObservationHike> observationList;

    public AdapterHike(Context context, List<ObservationHike> observationList) {
        this.context = context;
        this.observationList = observationList;
    }

    @Override
    public int getCount() {
        return observationList.size();
    }

    @Override
    public Object getItem(int position) {
        return observationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_observation, parent, false);
        }

        ObservationHike observation = observationList.get(position);

        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
        TextView locationTextView = convertView.findViewById(R.id.locationTextView);
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);
        TextView eventDescriptionTextView = convertView.findViewById(R.id.eventDescriptionTextView);
        TextView additionalInfoTextView = convertView.findViewById(R.id.additionalInfoTextView);
        TextView noteTextView = convertView.findViewById(R.id.noteTextView);
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);

        if (observation.getPhotoBitmap() != null) {
            photoImageView.setImageBitmap(observation.getPhotoBitmap());
            photoImageView.setVisibility(View.VISIBLE);
        } else {
            photoImageView.setVisibility(View.GONE);
        }

        locationTextView.setText("Location: " + observation.getLocation());
        timeTextView.setText("Time: " + observation.getTime());
        eventDescriptionTextView.setText("Event Description: " + observation.getEventDescription());
        additionalInfoTextView.setText("Additional Info: " + observation.getAdditionalInfo());
        noteTextView.setText("Note: " + observation.getNote());
        titleTextView.setText("Title: " + observation.getTitle());

        return convertView;
    }
}
