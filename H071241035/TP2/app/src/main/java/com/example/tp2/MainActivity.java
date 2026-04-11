package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFeed, rvStories;
    private FeedAdapter feedAdapter;
    private ImageView ivProfileNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFeed = findViewById(R.id.rv_feed);
        rvStories = findViewById(R.id.rv_stories);
        ivProfileNav = findViewById(R.id.iv_profile_nav);


        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new FeedAdapter(DataSource.feedPosts);
        rvFeed.setAdapter(feedAdapter);

        rvStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvStories.setAdapter(new StoryAdapter(DataSource.allStories));

        ivProfileNav.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("user", DataSource.mainUser);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        feedAdapter.notifyDataSetChanged();
    }
}
