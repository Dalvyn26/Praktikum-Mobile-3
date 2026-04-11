package com.example.tp2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        ImageView ivStoryDetail = findViewById(R.id.iv_story_detail);
        TextView tvStoryTitle = findViewById(R.id.tv_story_detail_title);

        Story story = getIntent().getParcelableExtra("story");
        if (story != null) {
            ivStoryDetail.setImageResource(story.getImageResource());
            tvStoryTitle.setText(story.getTitle());
        }
    }
}
