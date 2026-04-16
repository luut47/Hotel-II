package com.example.hotellapp.api;

import com.example.hotellapp.model.AuthResponse;
import com.example.hotellapp.model.LoginRequest;
import com.example.hotellapp.model.RegisterRequest;
import com.example.hotellapp.model.User;

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
