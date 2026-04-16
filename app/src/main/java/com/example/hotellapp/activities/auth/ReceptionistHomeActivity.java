package com.example.hotellapp.activities.auth;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotellapp.R;
import com.example.hotellapp.utils.SessionManager;

public class ReceptionistHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_role_home);

        SessionManager session = new SessionManager(this);
        TextView tvTitle = findViewById(R.id.tvRoleTitle);
        tvTitle.setText("Receptionist Home\nXin chào " + session.getFullName());
    }
}