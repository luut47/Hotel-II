package com.example.hotellapp.utils;
import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {
    private static final String PREF_NAME = "hotel_app_session";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ROLE = "role";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_LOGGED_IN = "logged_in";
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = prefs.edit();
    }
    public void saveLogin(String token, String role, String full_name, String email) {
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_FULL_NAME, full_name);
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.apply();
    }
    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
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

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
