package com.example.HikingApp_GCS200222;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListObservationsActivity extends AppCompatActivity {

    private ListView observationListView;
    private AdapterHike observationAdapter;
    private List<ObservationHike> observationList;
    private SharedPreferences sharedPreferences;
    private static final String OBSERVATION_PREFS = "observation_prefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_observations);

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
    }
}
