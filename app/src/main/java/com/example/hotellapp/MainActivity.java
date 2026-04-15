package com.example.hotellapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotellapp.activities.LoginActivity;
import com.example.hotellapp.activities.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGoLogin, btnGoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoLogin = findViewById(R.id.btnGoLogin);
        btnGoRegister = findViewById(R.id.btnGoRegister);

        btnGoLogin.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class))
        );

        btnGoRegister.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class))
        );
    }
}