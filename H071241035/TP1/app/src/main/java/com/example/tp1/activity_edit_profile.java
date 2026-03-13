package com.example.tp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_edit_profile extends AppCompatActivity {

    private EditText etName, etUsername, etPronouns, etBio;
    private Button btnSave;
    private ImageView ivBack, ivProfileImage;
    private TextView tvEditPicture;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivProfileImage.setImageURI(uri);
                    try {
                        final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                        getContentResolver().takePersistableUriPermission(uri, takeFlags);
                    } catch (SecurityException e) {
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etPronouns = findViewById(R.id.et_pronouns);
        etBio = findViewById(R.id.et_bio);
        btnSave = findViewById(R.id.btn_save);
        ivBack = findViewById(R.id.iv_back);
        ivProfileImage = findViewById(R.id.iv_edit_profile_image);
        tvEditPicture = findViewById(R.id.tv_edit_picture);

        Intent intent = getIntent();
        if (intent != null) {
            etName.setText(intent.getStringExtra("name"));
            etUsername.setText(intent.getStringExtra("username"));
            etPronouns.setText(intent.getStringExtra("pronouns"));
            etBio.setText(intent.getStringExtra("bio"));
            String uriString = intent.getStringExtra("imageUri");
            if (uriString != null) {
                selectedImageUri = Uri.parse(uriString);
                ivProfileImage.setImageURI(selectedImageUri);
            }
        }

        tvEditPicture.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));
        ivProfileImage.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", etName.getText().toString());
            resultIntent.putExtra("username", etUsername.getText().toString());
            resultIntent.putExtra("pronouns", etPronouns.getText().toString());
            resultIntent.putExtra("bio", etBio.getText().toString());
            if (selectedImageUri != null) {
                resultIntent.putExtra("imageUri", selectedImageUri.toString());
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        ivBack.setOnClickListener(v -> finish());
    }
}