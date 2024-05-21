package com.example.HikingApp_GCS200222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public void onLoginClick(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (sharedPreferences.contains(username)) {
            String savedPassword = sharedPreferences.getString(username, "");

            if (savedPassword.equals(password)) {
                Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                Intent mainScreenIntent = new Intent(this, Home.class);
                mainScreenIntent.putExtra("username", username);
                startActivity(mainScreenIntent);
            } else {
                Toast.makeText(this, "Password error", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegisterClick(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
