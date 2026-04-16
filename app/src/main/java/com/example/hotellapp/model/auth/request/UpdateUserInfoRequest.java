package com.example.hotellapp.model.auth.request;

public class UpdateUserInfoRequest {
    private String full_name;
    private String phone;
    private String citizen_id;
    private String address;

    private String avatar_url;

    public UpdateUserInfoRequest(String full_name, String phone, String citizen_id, String address, String avatar_url) {
        this.full_name = full_name;
        this.phone = phone;
        this.citizen_id = citizen_id;
        this.address = address;
        this.avatar_url = avatar_url;
    }

}
