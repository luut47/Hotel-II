package com.example.hotellapp.api;

import com.example.hotellapp.model.AuthResponse;
import com.example.hotellapp.model.LoginRequest;
import com.example.hotellapp.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ApiService {
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);
    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);
}
