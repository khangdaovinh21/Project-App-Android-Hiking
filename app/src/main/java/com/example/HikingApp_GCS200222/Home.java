package com.example.HikingApp_GCS200222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class Home extends AppCompatActivity {

    private TextView fullNameTextView, namescreenTextView;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        fullNameTextView = findViewById(R.id.fullNameTextView);
        namescreenTextView = findViewById(R.id.namescreenTextView);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        videoView = findViewById(R.id.videoView);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.your_video);

        videoView.setVideoURI(videoUri);

        videoView.start();

        if (getIntent().hasExtra("username")) {
            String username = getIntent().getStringExtra("username");
            String fullName = sharedPreferences.getString(username + "_fullname", "");
            String namescreen = sharedPreferences.getString(username + "_namescreen", "");

            fullNameTextView.setText(fullName);
            namescreenTextView.setText(namescreen);
        } else {
            fullNameTextView.setText("Full Name");
            namescreenTextView.setText("Name Screen");
        }

        Button addHikeButton = findViewById(R.id.addHikeButton);
        addHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addHikeIntent = new Intent(Home.this, AddAHike.class);
                startActivity(addHikeIntent);
            }
        });

        Button recordHikeButton = findViewById(R.id.recordHikeButton);
        recordHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleMapIntent = new Intent(Home.this, GoogleMapActivity.class);
                startActivity(googleMapIntent);
            }
        });

        Button planHikeButton = findViewById(R.id.planHikeButton);
        planHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent planHikeIntent = new Intent(Home.this, ObserveHikeActivity.class);
                startActivity(planHikeIntent);
            }
        });

        Button searchTrailsButton = findViewById(R.id.searchTrailsButton);
        searchTrailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchTrailsIntent = new Intent(Home.this, SearchTrailsActivity.class);
                startActivity(searchTrailsIntent);
            }
        });

        Button pedestrianRatingButton = findViewById(R.id.pedestrianRatingButton);
        pedestrianRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pedestrianRatingIntent = new Intent(Home.this, PedestrianRating.class);
                startActivity(pedestrianRatingIntent);
            }
        });

        Button hikingGuideButton = findViewById(R.id.hikingGuideButton);
        hikingGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hikingGuideIntent = new Intent(Home.this, HikingGuide.class);
                startActivity(hikingGuideIntent);
            }
        });

        Button hikingChatButton = findViewById(R.id.hikingChatButton);
        hikingChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hikingChatIntent = new Intent(Home.this, HikingChatActivity.class);
                startActivity(hikingChatIntent);
            }
        });

        Button setGoalButton = findViewById(R.id.setGoalButton);
        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setGoalIntent = new Intent(Home.this, SetGoalActivity.class);
                startActivity(setGoalIntent);
            }
        });

        // Add a "Log Out" button
        Button logOutButton = findViewById(R.id.logoutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the user's session data (e.g., username) and return to the login screen
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username"); // Remove the username or any other relevant session data
                editor.apply();

                // Navigate back to the login screen (replace LoginActivity.class with your actual login activity class)
                Intent loginIntent = new Intent(Home.this, MainActivity.class);
                startActivity(loginIntent);
                finish(); // Finish the current activity to prevent the user from returning to it via the back button
            }
        });
    }
}
