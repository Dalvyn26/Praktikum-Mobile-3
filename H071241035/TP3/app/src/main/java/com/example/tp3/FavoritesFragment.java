package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private View emptyState;
    private BookAdapter bookAdapter;

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
        ArrayList<Book> allBooks = DataDummy.getAllBooks();
        ArrayList<Book> favorites = new ArrayList<>();

        for (Book book : allBooks) {
            if (book.isLiked()) {
                favorites.add(book);
            }
        }

        bookAdapter.updateData(favorites);

        if (favorites.isEmpty()) {
            emptyState.setVisibility(View.VISIBLE);
            rvFavorites.setVisibility(View.GONE);
        } else {
            emptyState.setVisibility(View.GONE);
            rvFavorites.setVisibility(View.VISIBLE);
        }
    }
}
