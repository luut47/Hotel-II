package com.example.hotellapp.api;

import com.example.hotellapp.model.auth.AuthResponse;
import com.example.hotellapp.model.auth.LoginRequest;
import com.example.hotellapp.model.auth.RegisterRequest;
import com.example.hotellapp.model.auth.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);
    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);
    @PUT("user/me")
    Call<User> updateUserGuestInfo(@Body User user);
}
