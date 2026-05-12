package com.example.tp5;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat switchDarkMode;
    private ImageButton btnBack;
    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefsHelper = new SharedPreferencesHelper(this);

        switchDarkMode = findViewById(R.id.switch_dark_mode);
        btnBack = findViewById(R.id.btn_back);

        // Set switch state based on saved preference
        switchDarkMode.setChecked(prefsHelper.isDarkMode());

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefsHelper.setDarkMode(isChecked);
            
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            // recreate() is often needed to apply theme changes immediately to the current activity
            // but setDefaultNightMode usually handles it. Calling recreate ensures UI consistency.
            recreate();
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
