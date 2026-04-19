package com.example.tp3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private SearchView searchView;
    private ChipGroup chipGroupGenre;
    private String selectedGenre = "All";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBooks = view.findViewById(R.id.rvBooks);
        searchView = view.findViewById(R.id.searchView);
        chipGroupGenre = view.findViewById(R.id.chipGroupGenre);

        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), new ArrayList<>());
        rvBooks.setAdapter(bookAdapter);

        setupGenreFilters();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query, selectedGenre);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText, selectedGenre);
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        filterData(searchView.getQuery().toString(), selectedGenre);
    }

    private void setupGenreFilters() {
        chipGroupGenre.removeAllViews();

        ArrayList<String> genres = new ArrayList<>();
        genres.add("All");
        for (Book b : DataDummy.getAllBooks()) {
            if (!genres.contains(b.getGenre())) {
                genres.add(b.getGenre());
            }
        }

        for (String genre : genres) {
            Chip chip = new Chip(getContext());
            chip.setText(genre);
            chip.setCheckable(true);
            if (genre.equals("All")) {
                chip.setChecked(true);
            }
            chip.setOnClickListener(v -> {
                selectedGenre = genre;
                filterData(searchView.getQuery().toString(), selectedGenre);
            });
            chipGroupGenre.addView(chip);
        }
    }

    private void filterData(String query, String genre) {
        ArrayList<Book> allBooks = DataDummy.getAllBooks();
        ArrayList<Book> filtered = new ArrayList<>();

        for (Book book : allBooks) {
            boolean matchesSearch = TextUtils.isEmpty(query) || book.getTitle().toLowerCase().contains(query.toLowerCase());
            boolean matchesGenre = genre.equals("All") || book.getGenre().equals(genre);

            if (matchesSearch && matchesGenre) {
                filtered.add(book);
            }
        }
        bookAdapter.updateData(filtered);
    }
}
