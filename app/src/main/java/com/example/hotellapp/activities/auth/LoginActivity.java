package com.example.hotellapp.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.hotellapp.R;
import com.example.hotellapp.api.RetrofitClient;
import com.example.hotellapp.model.auth.AuthResponse;
import com.example.hotellapp.model.auth.LoginRequest;
import com.example.hotellapp.utils.SessionManager;
import com.example.hotellapp.utils.RoleRouter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity{
    private EditText edtEmail,edtPassword;
    private Button btnLogin;
    private TextView tvGoRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoRegister = findViewById(R.id.tvGoRegister);

        btnLogin.setOnClickListener(v -> doLogin());
        tvGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
    private void doLogin() {
        String email = edtEmail.getText().toString().trim().toLowerCase();
        String password = edtPassword.getText().toString().trim();

        if (!validateInput(email, password)) return;

        LoginRequest request = new LoginRequest(email, password);

        RetrofitClient.getApiService().login(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();

                    SessionManager sessionManager = new SessionManager(LoginActivity.this);
                    sessionManager.saveLogin(authResponse.getAccess_token(),authResponse.getUser());

                    Toast.makeText(LoginActivity.this,
                            "Đăng nhập thành công: " + authResponse.getUser().getRole_name(),
                            Toast.LENGTH_SHORT).show();

                    RoleRouter.goToHomeByRole(LoginActivity.this,
                            authResponse.getUser().getRole_name());
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Đăng nhập thất bại. Kiểm tra email hoặc mật khẩu.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Lỗi kết nối: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean validateInput(String email, String password) {
        if (email.isEmpty()) {
            edtEmail.setError("Nhập email");
            edtEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
            edtEmail.setError("Email phải có dạng *@gmail.com");
            edtEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            edtPassword.setError("Nhập mật khẩu");
            edtPassword.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            edtPassword.setError("Mật khẩu tối thiểu 8 ký tự");
            edtPassword.requestFocus();
            return false;
        }

        return true;
    }
}
