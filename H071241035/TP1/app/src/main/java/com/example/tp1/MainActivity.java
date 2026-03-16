package com.example.tp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvUsernameTop, tvName, tvPronouns, tvBio;
    private Button btnEditProfile;
    private ImageView ivProfileImage;
    private Uri currentProfileUri;
    private boolean isFirstTime = true;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    isFirstTime = false;
                    Intent data = result.getData();
                    String name = data.getStringExtra("name");
                    String username = data.getStringExtra("username");
                    String pronouns = data.getStringExtra("pronouns");
                    String bio = data.getStringExtra("bio");
                    String uriString = data.getStringExtra("imageUri");

                    tvName.setText(name);
                    tvUsernameTop.setText(username);
                    tvPronouns.setText(pronouns);
                    tvBio.setText(bio);

                    if (uriString != null) {
                        currentProfileUri = Uri.parse(uriString);
                        ivProfileImage.setImageURI(currentProfileUri);
                        try {
                            getContentResolver().takePersistableUriPermission(currentProfileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } catch (SecurityException e) {

                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvUsernameTop = findViewById(R.id.tv_username_top);
        tvName = findViewById(R.id.tv_name);
        tvPronouns = findViewById(R.id.tv_pronouns);
        tvBio = findViewById(R.id.tv_bio);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        ivProfileImage = findViewById(R.id.iv_profile_image);

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_edit_profile.class);
            if (!isFirstTime) {
                intent.putExtra("name", tvName.getText().toString());
                intent.putExtra("username", tvUsernameTop.getText().toString());
                intent.putExtra("pronouns", tvPronouns.getText().toString());
                intent.putExtra("bio", tvBio.getText().toString());
                if (currentProfileUri != null) {
                    intent.putExtra("imageUri", currentProfileUri.toString());
                }
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            editProfileLauncher.launch(intent);
        });
    }
}