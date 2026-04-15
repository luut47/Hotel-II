package com.example.hotellapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotellapp.utils.RoleRouter;
import com.example.hotellapp.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn() && sessionManager.getToken() != null) {
            RoleRouter.goToHomeByRole(this, sessionManager.getRole());
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}