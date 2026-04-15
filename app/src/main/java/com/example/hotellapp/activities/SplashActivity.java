package com.example.hotellapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotellapp.utils.SessionManager;
import com.example.hotellapp.utils.RoleRouter;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntancesState){
        super.onCreate(savedIntancesState);
        SessionManager sessionManager = new SessionManager(this);
        if(sessionManager.isLoggedIn() && sessionManager.getToken() != null){
            RoleRouter.goToHomeByRole(this, sessionManager.getRole());
        }else{
            startActivities(new android.content.Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void startActivities(Intent intent) {
    }
}
