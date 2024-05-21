package com.example.HikingApp_GCS200222;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class AddAHike extends AppCompatActivity {

    private EditText hikeNameEditText;
    private EditText hikeLocationEditText;
    private EditText hikeDateEditText;
    private CheckBox parkingCheckBox;
    private EditText hikeDistanceEditText;
    private Spinner difficultySpinner;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);

        hikeNameEditText = findViewById(R.id.hikeNameEditText);
        hikeLocationEditText = findViewById(R.id.hikeLocationEditText);
        hikeDateEditText = findViewById(R.id.hikeDateEditText);
        parkingCheckBox = findViewById(R.id.parkingCheckBox);
        hikeDistanceEditText = findViewById(R.id.hikeDistanceEditText);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        descriptionEditText = findViewById(R.id.descriptionEditText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        Button addHikeButton = findViewById(R.id.addHikeButton);
        addHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveHikeToSharedPreferences();
                    Toast.makeText(AddAHike.this, "successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            }
        });

        Button listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAHike.this, Listmain.class);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(AddAHike.this, Home.class);
                startActivity(mainIntent);
            }
        });
    }

    private boolean validateFields() {
        String hikeName = hikeNameEditText.getText().toString().trim();
        String hikeLocation = hikeLocationEditText.getText().toString().trim();
        String hikeDate = hikeDateEditText.getText().toString().trim();
        String hikeDistanceStr = hikeDistanceEditText.getText().toString().trim();
        String difficulty = difficultySpinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(hikeName) || TextUtils.isEmpty(hikeLocation) || TextUtils.isEmpty(hikeDate)
                || TextUtils.isEmpty(hikeDistanceStr) || TextUtils.isEmpty(difficulty)) {
            Toast.makeText(AddAHike.this, "Please enter in full", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            double hikeDistance = Double.parseDouble(hikeDistanceStr);
            if (hikeDistance < 0) {
                Toast.makeText(AddAHike.this, "Distance error", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(AddAHike.this, "Distance error", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void saveHikeToSharedPreferences() {
        // SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("HikeData", Context.MODE_PRIVATE);

        int hikeCount = sharedPreferences.getInt("HikeCount", 0) + 1;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("HikeName" + hikeCount, hikeNameEditText.getText().toString().trim());
        editor.putString("HikeLocation" + hikeCount, hikeLocationEditText.getText().toString().trim());
        editor.putString("HikeDate" + hikeCount, hikeDateEditText.getText().toString().trim());
        editor.putBoolean("HasParking" + hikeCount, parkingCheckBox.isChecked());
        editor.putString("HikeDistance" + hikeCount, hikeDistanceEditText.getText().toString().trim());
        editor.putString("Difficulty" + hikeCount, difficultySpinner.getSelectedItem().toString());
        editor.putString("Description" + hikeCount, descriptionEditText.getText().toString().trim());
        editor.putInt("HikeCount", hikeCount);
        editor.apply();
    }

    private void clearFields() {
        hikeNameEditText.setText("");
        hikeLocationEditText.setText("");
        hikeDateEditText.setText("");
        parkingCheckBox.setChecked(false);
        hikeDistanceEditText.setText("");
        difficultySpinner.setSelection(0);
        descriptionEditText.setText("");
    }
}
