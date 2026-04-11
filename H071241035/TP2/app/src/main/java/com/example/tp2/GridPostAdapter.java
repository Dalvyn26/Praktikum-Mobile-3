package com.example.tp2;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GridPostAdapter extends RecyclerView.Adapter<GridPostAdapter.ViewHolder> {
    private List<Post> postList;

    public GridPostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);
        if (post.getImageUri() != null) {
            holder.ivGridPost.setImageURI(Uri.parse(post.getImageUri()));
        } else {
            holder.ivGridPost.setImageResource(post.getImageResource());
        }

        holder.itemView.setOnClickListener(v -> {
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
        ImageView ivGridPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGridPost = itemView.findViewById(R.id.iv_grid_post);
        }
    }
}
