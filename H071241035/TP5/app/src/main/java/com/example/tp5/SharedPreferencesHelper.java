package com.example.tp5;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String AUTH_PREFS = "AuthPrefs";
    private static final String SETTINGS_PREFS = "SettingsPrefs";
    private static final String APP_DATA_PREFS = "AppDataPrefs";

    private SharedPreferences authPrefs;
    private SharedPreferences settingsPrefs;
    private SharedPreferences appDataPrefs;

    public SharedPreferencesHelper(Context context) {
        authPrefs = context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE);
        settingsPrefs = context.getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
        appDataPrefs = context.getSharedPreferences(APP_DATA_PREFS, Context.MODE_PRIVATE);
    }

    // Auth Operations
    public void registerUser(String username, String password) {
        authPrefs.edit().putString("username", username).putString("password", password).apply();
    }

    public boolean loginUser(String username, String password) {
        String savedUser = authPrefs.getString("username", "");
        String savedPass = authPrefs.getString("password", "");
        if (username.equals(savedUser) && password.equals(savedPass)) {
            setLoggedIn(true);
            return true;
        }
        return false;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        authPrefs.edit().putBoolean("is_logged_in", isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return authPrefs.getBoolean("is_logged_in", false);
    }

    public String getUsername() {
        return authPrefs.getString("username", "User");
    }

    public void deleteAccount() {
        authPrefs.edit().clear().apply();
        settingsPrefs.edit().clear().apply();
        appDataPrefs.edit().clear().apply();
    }

    // Image Operations (Base64)
    public void saveProfileImage(String base64Image) {
        appDataPrefs.edit().putString("profile_image", base64Image).apply();
    }

    public String getProfileImage() {
        return appDataPrefs.getString("profile_image", null);
    }

    // Brew Method Operations (CRUD)
    public void saveBrewMethod(String method) {
        appDataPrefs.edit().putString("favorite_brew", method).apply();
    }

    public String getBrewMethod() {
        return appDataPrefs.getString("favorite_brew", "");
    }

    // Settings Operations
    public void setDarkMode(boolean isEnabled) {
        settingsPrefs.edit().putBoolean("dark_mode", isEnabled).apply();
    }

    public boolean isDarkMode() {
        return settingsPrefs.getBoolean("dark_mode", false);
    }

    public void clearSession() {
        setLoggedIn(false);
    }
}
