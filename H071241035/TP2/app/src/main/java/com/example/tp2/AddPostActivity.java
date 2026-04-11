package com.example.tp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.card.MaterialCardView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AddPostActivity extends AppCompatActivity {

    private ImageView ivPreview;
    private TextView tvChooseImage;
    private MaterialCardView flChooseImage;
    private EditText etCaption;
    private Button btnUpload;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> pickImage = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivPreview.setImageURI(uri);
                    ivPreview.setVisibility(View.VISIBLE);
                    tvChooseImage.setVisibility(View.GONE);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        ivPreview = findViewById(R.id.iv_preview);
        tvChooseImage = findViewById(R.id.tv_choose_image);
        flChooseImage = findViewById(R.id.fl_choose_image);
        etCaption = findViewById(R.id.et_caption);
        btnUpload = findViewById(R.id.btn_upload);

        flChooseImage.setOnClickListener(v -> pickImage.launch("image/*"));

        btnUpload.setOnClickListener(v -> {
            String caption = etCaption.getText().toString().trim();
            if (selectedImageUri == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (caption.isEmpty()) {
                Toast.makeText(this, "Please enter a caption", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add new post
            Post newPost = new Post(DataSource.mainUser, selectedImageUri.toString(), caption);
            DataSource.feedPosts.add(0, newPost);

            Toast.makeText(this, "Post uploaded successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
