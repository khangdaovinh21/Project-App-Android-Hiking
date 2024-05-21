package com.example.HikingApp_GCS200222;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetGoalActivity extends AppCompatActivity {

    private TextView goalTextView;
    private EditText hikesGoalEditText, distanceGoalEditText;
    private ListView goalsListView;
    private ArrayList<String> goalsList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);
        goalTextView = findViewById(R.id.goalTextView);
        hikesGoalEditText = findViewById(R.id.hikesGoalEditText);
        distanceGoalEditText = findViewById(R.id.distanceGoalEditText);
        goalsListView = findViewById(R.id.goalsListView);

        goalsList = new ArrayList<>(); // Initialize the list of goals

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyGoals", MODE_PRIVATE);

        // Load and display saved goals from SharedPreferences
        Set<String> savedGoalsSet = sharedPreferences.getStringSet("goalsSet", new HashSet<>());
        goalsList.addAll(savedGoalsSet);
        updateGoalsListView();

        // Set an OnItemLongClickListener to allow deleting goals
        goalsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove the goal from the list
                String goalToRemove = goalsList.get(position);
                goalsList.remove(position);
                updateGoalsListView();

                // Remove the goal from SharedPreferences
                removeGoalFromSharedPreferences(goalToRemove);

                return true;
            }
        });

        Button setGoalButton = findViewById(R.id.setGoalButton);
        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set and display personal goals
                String hikesGoal = hikesGoalEditText.getText().toString();
                String distanceGoal = distanceGoalEditText.getText().toString();

                String goalText = "Number of Hikes Goal: " + hikesGoal + "\nDistance Goal (in km): " + distanceGoal;
                goalTextView.setText(goalText);
            }
        });

        Button saveGoalButton = findViewById(R.id.saveGoalButton);
        saveGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the goals in the list
                String hikesGoal = hikesGoalEditText.getText().toString();
                String distanceGoal = distanceGoalEditText.getText().toString();

                String goalText = "Number of Hikes Goal: " + hikesGoal + "\nDistance Goal (in km): " + distanceGoal;
                goalsList.add(goalText);
                updateGoalsListView();

                // Save the goals in SharedPreferences
                saveGoalToSharedPreferences(goalText);

                // Clear the input fields
                hikesGoalEditText.getText().clear();
                distanceGoalEditText.getText().clear();
            }
        });

        Button backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the MainScreenActivity when the "Back to MainScreen" button is clicked
                finish(); // Finish the current activity
            }
        });
    }

    private void updateGoalsListView() {
        ArrayAdapter<String> goalsAdapter = new ArrayAdapter<>(SetGoalActivity.this, android.R.layout.simple_list_item_1, goalsList);
        goalsListView.setAdapter(goalsAdapter);
    }

    private void saveGoalToSharedPreferences(String goal) {
        Set<String> goalsSet = new HashSet<>(sharedPreferences.getStringSet("goalsSet", new HashSet<>()));
        goalsSet.add(goal);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("goalsSet", goalsSet);
        editor.apply();
    }

    private void removeGoalFromSharedPreferences(String goal) {
        Set<String> goalsSet = new HashSet<>(sharedPreferences.getStringSet("goalsSet", new HashSet<>()));
        goalsSet.remove(goal);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("goalsSet", goalsSet);
        editor.apply();
    }
}
