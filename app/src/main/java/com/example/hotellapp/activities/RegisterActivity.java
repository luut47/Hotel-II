package com.example.hotellapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotellapp.R;
import com.example.hotellapp.api.RetrofitClient;
import com.example.hotellapp.model.AuthResponse;
import com.example.hotellapp.model.RegisterRequest;
import com.example.hotellapp.utils.RoleRouter;
import com.example.hotellapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtFullName, edtEmail, edtPhone, edtPassword, edtCitizenId, edtAddress;
    private Button btnRegister;
    private TextView tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_register);

        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtCitizenId = findViewById(R.id.edtCitizenId);
        edtAddress = findViewById(R.id.edtAddress);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoLogin = findViewById(R.id.tvGoRegister);
        btnRegister.setOnClickListener(v -> doRegister());

        tvGoLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void doRegister() {
        String fullName = edtFullName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim().toLowerCase();
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String citizenId = edtCitizenId.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if (!validateInput(fullName, email, phone, password, citizenId)) return;

        RegisterRequest request = new RegisterRequest(fullName,email,phone.isEmpty()?null:phone,
                password,citizenId.isEmpty()?null : citizenId,address.isEmpty() ? null : address);

        RetrofitClient.getApiService().register(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();

                    SessionManager sessionManager = new SessionManager(RegisterActivity.this);
                    sessionManager.saveLogin(
                            authResponse.getAccess_token(),
                            authResponse.getUser()
                    );

                    Toast.makeText(RegisterActivity.this,
                            "Đăng ký thành công",
                            Toast.LENGTH_SHORT).show();

                    RoleRouter.goToHomeByRole(RegisterActivity.this,
                            authResponse.getUser().getRole_name());
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Đăng ký thất bại. Có thể email đã tồn tại.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,
                        "Lỗi kết nối: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateInput(String fullName, String email, String phone,
                                  String password, String citizenId) {
        if (fullName.length() < 2) {
            edtFullName.setError("Họ tên tối thiểu 2 ký tự");
            edtFullName.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
            edtEmail.setError("Email phải có dạng *@gmail.com");
            edtEmail.requestFocus();
            return false;
        }

        if (!phone.isEmpty() && !phone.matches("\\d{10}")) {
            edtPhone.setError("Số điện thoại phải đúng 10 số");
            edtPhone.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            edtPassword.setError("Mật khẩu tối thiểu 8 ký tự");
            edtPassword.requestFocus();
            return false;
        }

        if (!citizenId.isEmpty() && !citizenId.matches("\\d{12}")) {
            edtCitizenId.setError("CCCD phải đúng 12 số");
            edtCitizenId.requestFocus();
            return false;
        }

        return true;
    }
}