package com.data.catatanharianagus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(v -> login());
        buttonRegister.setOnClickListener(v -> goToRegisterPage());
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String registeredPassword = sharedPreferences.getString(username, null);

        if (registeredPassword != null && registeredPassword.equals(password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_LONG).show();
        }
    }

    private void goToRegisterPage() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
