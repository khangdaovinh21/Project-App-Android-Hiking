package com.example.HikingApp_GCS200222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObserveHikeActivity extends AppCompatActivity {

    private EditText locationEditText, timeEditText, eventDescriptionEditText,
            additionalInfoEditText, noteEditText, titleEditText;
    private Button addButton, clearButton, backButton, takePhotoButton;
    private ListView observationListView;
    private AdapterHike observationAdapter;
    private List<ObservationHike> observationList;
    private ImageView photoImageView;
    private SharedPreferences sharedPreferences;
    private static final String OBSERVATION_PREFS = "observation_prefs";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_hike);

        locationEditText = findViewById(R.id.locationEditText);
        timeEditText = findViewById(R.id.timeEditText);
        eventDescriptionEditText = findViewById(R.id.eventDescriptionEditText);
        additionalInfoEditText = findViewById(R.id.additionalInfoEditText);
        noteEditText = findViewById(R.id.noteEditText);
        titleEditText = findViewById(R.id.titleEditText);

        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);
        backButton = findViewById(R.id.backButton);
        takePhotoButton = findViewById(R.id.takePhotoButton);
        photoImageView = findViewById(R.id.photoImageView);

        observationListView = findViewById(R.id.observationListView);

        sharedPreferences = getSharedPreferences(OBSERVATION_PREFS, MODE_PRIVATE);
        String json = sharedPreferences.getString("observations", null);
        Type type = new TypeToken<List<ObservationHike>>() {}.getType();
        observationList = new ArrayList<>();
        if (json != null) {
            observationList = new Gson().fromJson(json, type);
        }

        observationAdapter = new AdapterHike(this, observationList);
        observationListView.setAdapter(observationAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addObservation();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearObservations();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObserveHikeActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        observationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeObservation(position);
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photoImageView.setImageBitmap(imageBitmap);
            photoImageView.setVisibility(View.VISIBLE);
        }
    }

    private void addObservation() {
        String location = locationEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String eventDescription = eventDescriptionEditText.getText().toString();
        String additionalInfo = additionalInfoEditText.getText().toString();
        String note = noteEditText.getText().toString();
        String title = titleEditText.getText().toString();

        if (location.isEmpty() || time.isEmpty() || eventDescription.isEmpty() ||
                additionalInfo.isEmpty() || note.isEmpty() || title.isEmpty()) {
            Toast.makeText(this, "Not enough information", Toast.LENGTH_SHORT).show();
            return;
        }

        ObservationHike observation = new ObservationHike(location, time, eventDescription, additionalInfo, note, title);

        if (photoImageView.getVisibility() == View.VISIBLE) {
            observation.setPhotoBitmap(((BitmapDrawable) photoImageView.getDrawable()).getBitmap());
        }

        observationList.add(observation);
        observationAdapter.notifyDataSetChanged();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("observations", new Gson().toJson(observationList));
        editor.apply();

        locationEditText.setText("");
        timeEditText.setText("");
        eventDescriptionEditText.setText("");
        additionalInfoEditText.setText("");
        noteEditText.setText("");
        titleEditText.setText("");
        photoImageView.setVisibility(View.GONE);
    }

    private void clearObservations() {
        observationList.clear();
        observationAdapter.notifyDataSetChanged();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("observations");
        editor.apply();
    }

    private void removeObservation(int position) {
        observationList.remove(position);
        observationAdapter.notifyDataSetChanged();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("observations", new Gson().toJson(observationList));
        editor.apply();
    }
}
