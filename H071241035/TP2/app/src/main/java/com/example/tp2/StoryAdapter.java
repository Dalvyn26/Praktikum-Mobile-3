package com.example.tp2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private List<Story> storyList;

    public StoryAdapter(List<Story> storyList) {
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = storyList.get(position);
        holder.ivStory.setImageResource(story.getImageResource());
        holder.tvStoryTitle.setText(story.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailStoryActivity.class);
            intent.putExtra("story", story);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStory;
        TextView tvStoryTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStory = itemView.findViewById(R.id.iv_story);
            tvStoryTitle = itemView.findViewById(R.id.tv_story_title);
        }
    }
}
