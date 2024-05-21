package com.example.HikingApp_GCS200222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameEditText, usernameEditText, passwordEditText, confirmPasswordEditText, namescreenEditText;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        namescreenEditText = findViewById(R.id.namescreenEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public void onRegisterClick(View view) {
        String fullName = fullNameEditText.getText().toString();
        String namescreen = namescreenEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (validateRegistration(fullName, namescreen, username, password, confirmPassword)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(username, password);
            editor.putString(username + "_fullname", fullName);
            editor.putString(username + "_namescreen", namescreen);
            editor.apply();

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateRegistration(String fullName, String namescreen, String username, String password, String confirmPassword) {
        if (fullName.isEmpty() || namescreen.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }


        return true;
    }
}
