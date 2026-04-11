package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ShapeableImageView ivProfile;
    private TextView tvFullName, tvPostsCount;
    private RecyclerView rvHighlights, rvPosts;
    private GridPostAdapter postAdapter;
    private ImageView ivAddPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfile = findViewById(R.id.iv_profile_main);
        tvFullName = findViewById(R.id.tv_profile_fullname);
        tvPostsCount = findViewById(R.id.tv_posts_count);
        rvHighlights = findViewById(R.id.rv_highlights);
        rvPosts = findViewById(R.id.rv_profile_posts);
        ivAddPost = findViewById(R.id.iv_profile_add_post);

        User user = getIntent().getParcelableExtra("user");
        if (user == null) {
            user = DataSource.mainUser;
        }

        ivProfile.setImageResource(user.getProfileImage());
        tvFullName.setText(user.getFullName());

        // Check if this is the main user's profile to show "Add Post"
        if (user.getUsername().equals(DataSource.mainUser.getUsername())) {
            ivAddPost.setVisibility(View.VISIBLE);
            ivAddPost.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, AddPostActivity.class);
                startActivity(intent);
            });
        } else {
            ivAddPost.setVisibility(View.GONE);
        }

        // Highlights - Specific to user
        List<Story> userStories = DataSource.getStoriesByUser(user.getUsername());
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlights.setAdapter(new StoryAdapter(userStories));

        // Filter posts by user
        updatePosts(user);
    }

    private void updatePosts(User user) {
        List<Post> userPosts = DataSource.getPostsByUser(user.getUsername());
        tvPostsCount.setText(String.valueOf(userPosts.size()));

        rvPosts.setLayoutManager(new GridLayoutManager(this, 3));
        postAdapter = new GridPostAdapter(userPosts);
        rvPosts.setAdapter(postAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = getIntent().getParcelableExtra("user");
        if (user == null) user = DataSource.mainUser;
        updatePosts(user);
    }
}
