package com.example.HikingApp_GCS200222;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

import java.util.ArrayList;
import java.util.List;

public class Listmain extends AppCompatActivity {

    private ListView hikeListView;
    private HikeAdapter adapter;
    private List<Hike> hikeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        hikeListView = findViewById(R.id.hikeListView);
        hikeList = new ArrayList<>();
        adapter = new HikeAdapter(this, hikeList);

        hikeListView.setAdapter(adapter);

        readHikeDataFromSharedPreferences();

        Button backToAddHikeButton = findViewById(R.id.backToAddHikeButton);
        backToAddHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listmain.this, AddAHike.class);
                startActivity(intent);
            }
        });

        hikeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                showDeleteConfirmationDialog(position);
            }
        });

        Button searchButton = findViewById(R.id.searchButton);
        EditText searchEditText = findViewById(R.id.searchEditText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchKeyword = searchEditText.getText().toString().trim().toLowerCase();

                List<Hike> searchResults = new ArrayList<>();

                for (Hike hike : hikeList) {
                    String hikeName = hike.getHikeName().toLowerCase();
                    String hikeLocation = hike.getHikeLocation().toLowerCase();
                    String hikeDate = hike.getHikeDate().toLowerCase();
                    String hikeDistance = String.valueOf(hike.getHikeDistance());
                    hikeDistance = hikeDistance.toLowerCase();
                    String difficulty = hike.getDifficulty().toLowerCase();
                    String description = hike.getDescription().toLowerCase();

                    if (hikeName.contains(searchKeyword) || hikeLocation.contains(searchKeyword) || hikeDate.contains(searchKeyword) ||
                            hikeDistance.contains(searchKeyword) || difficulty.contains(searchKeyword) || description.contains(searchKeyword)) {
                        searchResults.add(hike);
                    }
                }

                adapter.clear();
                adapter.addAll(searchResults);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void readHikeDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("HikeData", Context.MODE_PRIVATE);
        int hikeCount = sharedPreferences.getInt("HikeCount", 0);

        for (int i = 1; i <= hikeCount; i++) {
            String hikeName = sharedPreferences.getString("HikeName" + i, "");
            String hikeLocation = sharedPreferences.getString("HikeLocation" + i, "");
            String hikeDate = sharedPreferences.getString("HikeDate" + i, "");
            String hikeDistance = sharedPreferences.getString("HikeDistance" + i, "");
            String difficulty = sharedPreferences.getString("Difficulty" + i, "");
            String description = sharedPreferences.getString("Description" + i, "");

            Hike hike = new Hike(hikeName, hikeLocation, hikeDate, hikeDistance, difficulty, description);
            hikeList.add(hike);
        }

        adapter.notifyDataSetChanged();
    }

    private void saveHikeDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("HikeData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("HikeCount", hikeList.size());

        for (int i = 0; i < hikeList.size(); i++) {
            Hike hike = hikeList.get(i);
            editor.putString("HikeName" + (i + 1), hike.getHikeName());
            editor.putString("HikeLocation" + (i + 1), hike.getHikeLocation());
            editor.putString("HikeDate" + (i + 1), hike.getHikeDate());
            editor.putString("HikeDistance" + (i + 1), hike.getHikeDistance());
            editor.putString("Difficulty" + (i + 1), hike.getDifficulty());
            editor.putString("Description" + (i + 1), hike.getDescription());
        }

        editor.apply();
    }

    private void showDeleteConfirmationDialog(final int position) {
        DeleteConfirmationDialog dialog = new DeleteConfirmationDialog(this);
        dialog.setDeleteListener(new DeleteConfirmationDialog.OnDeleteListener() {
            @Override
            public void onDeleteConfirmed() {
                deleteHike(position);
            }
        });
        dialog.show();
    }

    private void deleteHike(int position) {
        hikeList.remove(position);

        adapter.notifyDataSetChanged();

        saveHikeDataToSharedPreferences();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Listmain.this, AddAHike.class);
        startActivity(intent);
    }
}
