package com.example.tp2;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<Post> postList;

    public FeedAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.ivProfile.setImageResource(post.getUser().getProfileImage());
        holder.tvUsername.setText(post.getUser().getUsername());
        
        if (post.getImageUri() != null) {
            holder.ivPost.setImageURI(Uri.parse(post.getImageUri()));
        } else {
            holder.ivPost.setImageResource(post.getImageResource());
        }

        holder.tvCaption.setText(post.getCaption());

        holder.layoutUser.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
            intent.putExtra("user", post.getUser());
            v.getContext().startActivity(intent);
        });

        holder.ivPost.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailPostActivity.class);
            intent.putExtra("post", post);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivPost;
        TextView tvUsername, tvCaption;
        LinearLayout layoutUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            ivPost = itemView.findViewById(R.id.iv_post);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            layoutUser = itemView.findViewById(R.id.layout_user);
        }
    }
}
