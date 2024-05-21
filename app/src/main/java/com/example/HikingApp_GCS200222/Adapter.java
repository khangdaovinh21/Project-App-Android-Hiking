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

public class Adapter extends BaseAdapter {
    private Context context;
    private List<ObservationHike> observationList;

    public Adapter(Context context, List<ObservationHike> observationList) {
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
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ObservationHike observation = observationList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView locationTextView = convertView.findViewById(R.id.locationTextView);
        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);

        titleTextView.setText(observation.getTitle());
        locationTextView.setText(observation.getLocation());

        // Set the photo if available
        if (observation.getPhotoBitmap() != null) {
            photoImageView.setImageBitmap(observation.getPhotoBitmap());
            photoImageView.setVisibility(View.VISIBLE);
        } else {
            photoImageView.setVisibility(View.GONE);
        }

        return convertView;
    }
}
