package com.example.tp5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private EditText etBrewMethod;
    private ImageView ivProfile;
    private Button btnSaveBrew, btnSettings, btnLogout, btnDeleteAccount;
    private SharedPreferencesHelper prefsHelper;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    processAndSaveImage(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefsHelper = new SharedPreferencesHelper(this);

        tvGreeting = findViewById(R.id.tv_greeting);
        etBrewMethod = findViewById(R.id.et_brew_method);
        ivProfile = findViewById(R.id.iv_profile);
        btnSaveBrew = findViewById(R.id.btn_save_brew);
        btnSettings = findViewById(R.id.btn_settings);
        btnLogout = findViewById(R.id.btn_logout);
        btnDeleteAccount = findViewById(R.id.btn_delete_account);

        // Display Greeting
        tvGreeting.setText("Halo NIM, " + prefsHelper.getUsername() + "!");

        // Load profile image
        loadProfileImage();

        ivProfile.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        // Load saved brew method (Read Operation)
        etBrewMethod.setText(prefsHelper.getBrewMethod());

        // Save brew method (Create/Update Operation)
        btnSaveBrew.setOnClickListener(v -> {
            String method = etBrewMethod.getText().toString().trim();
            if (!method.isEmpty()) {
                prefsHelper.saveBrewMethod(method);
                Toast.makeText(this, "Metode seduh disimpan!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Masukkan metode seduh", Toast.LENGTH_SHORT).show();
            }
        });

        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            prefsHelper.clearSession();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnDeleteAccount.setOnClickListener(v -> showDeleteConfirmation());
    }

    private void loadProfileImage() {
        String base64Image = prefsHelper.getProfileImage();
        if (base64Image != null) {
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivProfile.setImageBitmap(decodedByte);
        }
    }

    private void processAndSaveImage(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            
            // Resize bitmap to keep Base64 string reasonably small
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
            
            prefsHelper.saveProfileImage(base64Image);
            ivProfile.setImageBitmap(resizedBitmap);
            Toast.makeText(this, "Foto profil diperbarui!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal memproses gambar", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Akun")
                .setMessage("Apakah Anda yakin ingin menghapus akun? Semua data akan hilang.")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    prefsHelper.deleteAccount();
                    Toast.makeText(this, "Akun dihapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh brew method if changed elsewhere (though not likely in this simple app)
        etBrewMethod.setText(prefsHelper.getBrewMethod());
    }
}