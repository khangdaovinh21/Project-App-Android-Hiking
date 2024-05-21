package com.example.HikingApp_GCS200222;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

import java.util.ArrayList;
import java.util.List;

public class PedestrianRating extends AppCompatActivity {

    private EditText hikingDistanceEditText, completionTimeEditText;
    private ListView ratingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedestrian_rating);

        hikingDistanceEditText = findViewById(R.id.hikingDistanceEditText);
        completionTimeEditText = findViewById(R.id.completionTimeEditText);
        ratingListView = findViewById(R.id.ratingListView);

        Button ratingButton = findViewById(R.id.ratingButton);
        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayRating();
            }
        });

        Button backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMainScreen();
            }
        });
    }

    private void calculateAndDisplayRating() {
        double hikingDistance = Double.parseDouble(hikingDistanceEditText.getText().toString());
        double completionTime = Double.parseDouble(completionTimeEditText.getText().toString());

        List<RatingLevel> ratingLevels = calculateRating(hikingDistance, completionTime);

        RatingAdapter ratingAdapter = new RatingAdapter(this, ratingLevels);
        ratingListView.setAdapter(ratingAdapter);
    }

    private List<RatingLevel> calculateRating(double hikingDistance, double completionTime) {
        List<RatingLevel> ratingLevels = new ArrayList<>();

        if (hikingDistance < 5 && completionTime < 2) {
            ratingLevels.add(new RatingLevel("Easy Level", "Hiking Distance: Less than 5 km\nCompletion Time: Less than 2 hours"));
        }
        if (hikingDistance >= 5 && hikingDistance <= 10 && completionTime >= 2 && completionTime <= 4) {
            ratingLevels.add(new RatingLevel("Moderate Level", "Hiking Distance: 5-10 km\nCompletion Time: 2-4 hours"));
        }
        if (hikingDistance > 10 && hikingDistance <= 20 && completionTime > 4 && completionTime <= 8) {
            ratingLevels.add(new RatingLevel("Difficult Level", "Hiking Distance: 10-20 km\nCompletion Time: 4-8 hours"));
        }
        if (hikingDistance > 20 && completionTime > 8) {
            ratingLevels.add(new RatingLevel("Very Difficult Level", "Hiking Distance: More than 20 km\nCompletion Time: More than 8 hours"));
        }

        return ratingLevels;
    }

    private void navigateBackToMainScreen() {
        Intent mainIntent = new Intent(this, Home.class);
        startActivity(mainIntent);
    }
}
