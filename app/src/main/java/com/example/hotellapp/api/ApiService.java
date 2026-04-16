package com.example.hotellapp.api;

import com.example.hotellapp.model.auth.request.UpdateUserInfoRequest;
import com.example.hotellapp.model.auth.response.AuthResponse;
import com.example.hotellapp.model.auth.request.LoginRequest;
import com.example.hotellapp.model.auth.request.RegisterRequest;
import com.example.hotellapp.model.auth.response.UpdateUserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);
    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);
    @PUT("user/me")
    Call<UpdateUserInfoResponse> updateUserGuestInfo(
            @Header("Authorization") String authorization,
            @Body UpdateUserInfoRequest request
    );
}
