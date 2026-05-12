package com.example.tp3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private View emptyState;
    private ProgressBar progressBar;
    private BookAdapter bookAdapter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorites = view.findViewById(R.id.rvFavorites);
        emptyState = view.findViewById(R.id.emptyState);
        progressBar = view.findViewById(R.id.progressBar);

        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), new ArrayList<>());
        rvFavorites.setAdapter(bookAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        progressBar.setVisibility(View.VISIBLE);
        rvFavorites.setVisibility(View.GONE);
        emptyState.setVisibility(View.GONE);

        executor.execute(() -> {
            try {
                // Simulate processing time
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Book> allBooks = DataDummy.getAllBooks();
            ArrayList<Book> favorites = new ArrayList<>();

            for (Book book : allBooks) {
                if (book.isLiked()) {
                    favorites.add(book);
                }
            }

            handler.post(() -> {
                bookAdapter.updateData(favorites);
                progressBar.setVisibility(View.GONE);

                if (favorites.isEmpty()) {
                    emptyState.setVisibility(View.VISIBLE);
                    rvFavorites.setVisibility(View.GONE);
                } else {
                    emptyState.setVisibility(View.GONE);
                    rvFavorites.setVisibility(View.VISIBLE);
                }
            });
        });
    }
}
