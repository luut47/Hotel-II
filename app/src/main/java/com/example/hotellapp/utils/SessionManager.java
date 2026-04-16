package com.example.hotellapp.utils;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotellapp.model.User;

public class SessionManager {
    private static final String PREF_NAME = "hotel_app_session";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_USER_ID = "user_id";

    private static final String KEY_ROLE = "role";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_CITIZEN_ID = "citizen_id";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_STATUS = "status";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = prefs.edit();
    }
    public void saveLogin(String token, User user) {
        editor.putString(KEY_TOKEN, token);
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, user.getUser_id());
        editor.putString(KEY_ROLE, user.getRole_name());
        editor.putString(KEY_FULL_NAME, user.getFull_name());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.putString(KEY_CITIZEN_ID, user.getCitizen_id());
        editor.putString(KEY_ADDRESS, user.getAddress());
        editor.putString(KEY_AVATAR_URL, user.getAvatar_url());
        editor.putString(KEY_STATUS, user.getStatus());

        editor.apply();
    }
    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }
    public int getUserId() {
        return prefs.getInt(KEY_USER_ID, -1);
    }
    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }
    public String getRole() {
        return prefs.getString(KEY_ROLE, "");
    }
    public String getFullName() {
        return prefs.getString(KEY_FULL_NAME, "");
    }
    public String getEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }
    public String getPhone() {
        return prefs.getString(KEY_PHONE, "");
    }
    public String getCitizenId() {
        return prefs.getString(KEY_CITIZEN_ID, "");
    }
    public String getAddress() {
        return prefs.getString(KEY_ADDRESS, "");
    }
    public String getAvatarUrl() {
        return prefs.getString(KEY_AVATAR_URL, "");
    }
    public String getStatus() {
        return prefs.getString(KEY_STATUS, "");
    }
    public void logout() {
        editor.clear();
        editor.apply();
    }
}
