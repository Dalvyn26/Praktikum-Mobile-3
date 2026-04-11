package com.example.tp2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        ShapeableImageView ivProfile = findViewById(R.id.iv_detail_profile);
        TextView tvUsername = findViewById(R.id.tv_detail_username);
        ImageView ivPost = findViewById(R.id.iv_detail_post);
        TextView tvCaption = findViewById(R.id.tv_detail_caption);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        Post post = getIntent().getParcelableExtra("post");
        if (post != null) {
            ivProfile.setImageResource(post.getUser().getProfileImage());
            tvUsername.setText(post.getUser().getUsername());
            
            if (post.getImageUri() != null) {
                ivPost.setImageURI(Uri.parse(post.getImageUri()));
            } else {
                ivPost.setImageResource(post.getImageResource());
            }
            
            tvCaption.setText(post.getCaption());
        }
    }
}
