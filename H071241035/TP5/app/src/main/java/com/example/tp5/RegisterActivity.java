package com.example.tp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private TextInputLayout tilUsername, tilPassword;
    private ImageView ivProfile;
    private Button btnRegister;
    private TextView tvLoginLink;
    private SharedPreferencesHelper prefsHelper;
    private String base64Image = null;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    processImage(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prefsHelper = new SharedPreferencesHelper(this);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        ivProfile = findViewById(R.id.iv_profile_register);
        tilUsername = (TextInputLayout) etUsername.getParent().getParent();
        tilPassword = (TextInputLayout) etPassword.getParent().getParent();
        btnRegister = findViewById(R.id.btn_register);
        tvLoginLink = findViewById(R.id.tv_login_link);

        ivProfile.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            tilUsername.setError(null);
            tilPassword.setError(null);

            if (username.isEmpty()) {
                tilUsername.setError("NIM tidak boleh kosong");
                return;
            }
            if (password.isEmpty()) {
                tilPassword.setError("Password tidak boleh kosong");
                return;
            }

            prefsHelper.registerUser(username, password);
            if (base64Image != null) {
                prefsHelper.saveProfileImage(base64Image);
            }
            Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
            
            startActivity(new android.content.Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        tvLoginLink.setOnClickListener(v -> {
            startActivity(new android.content.Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void processImage(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
            
            ivProfile.setImageBitmap(resizedBitmap);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal memproses gambar", Toast.LENGTH_SHORT).show();
        }
    }
}
