package com.example.tp3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK = "extra_book";
    
    private ImageView ivDetailCover;
    private TextView tvDetailTitle, tvDetailAuthorYear, tvDetailRating, tvDetailBlurb;
    private Chip chipDetailGenre;
    private FloatingActionButton fabLike;
    private Book currentBook;
    private boolean isLiked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ivDetailCover = findViewById(R.id.ivDetailCover);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailAuthorYear = findViewById(R.id.tvDetailAuthorYear);
        tvDetailRating = findViewById(R.id.tvDetailRating);
        tvDetailBlurb = findViewById(R.id.tvDetailBlurb);
        chipDetailGenre = findViewById(R.id.chipDetailGenre);
        fabLike = findViewById(R.id.fabLike);

        currentBook = getIntent().getParcelableExtra(EXTRA_BOOK);

        if (currentBook != null) {
            setupData();
        } else {
            Toast.makeText(this, "Data buku tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupData() {
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle(""); // Hide title on collapse to use our own TextView

        tvDetailTitle.setText(currentBook.getTitle());
        tvDetailAuthorYear.setText(currentBook.getAuthor() + " • " + currentBook.getYear());
        tvDetailRating.setText(String.valueOf(currentBook.getRating()));
        tvDetailBlurb.setText(currentBook.getBlurb());
        chipDetailGenre.setText(currentBook.getGenre());

        Glide.with(this)
                .load(currentBook.getImagePath())
                .centerCrop()
                .into(ivDetailCover);

        // check global list for liked status if it was updated
        for (Book b : DataDummy.getAllBooks()) {
            if (b.getTitle().equals(currentBook.getTitle())) {
                isLiked = b.isLiked();
                break;
            }
        }
        
        updateLikeIcon(false);

        fabLike.setOnClickListener(v -> {
            isLiked = !isLiked;
            // update global
            for (Book b : DataDummy.getAllBooks()) {
                if (b.getTitle().equals(currentBook.getTitle())) {
                    b.setLiked(isLiked);
                    break;
                }
            }
            updateLikeIcon(true);
        });
    }

    private void updateLikeIcon(boolean animate) {
        if (isLiked) {
            fabLike.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on, getTheme()));
            fabLike.setColorFilter(getResources().getColor(R.color.white, getTheme()));
            fabLike.setBackgroundTintList(android.content.res.ColorStateList.valueOf(getResources().getColor(R.color.md_theme_light_error, getTheme())));
        } else {
            fabLike.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_off, getTheme()));
            fabLike.setColorFilter(getResources().getColor(R.color.md_theme_light_onSurfaceVariant, getTheme()));
            fabLike.setBackgroundTintList(android.content.res.ColorStateList.valueOf(getResources().getColor(R.color.md_theme_light_surfaceVariant, getTheme())));
        }

        if (animate) {
            fabLike.animate().scaleX(1.2f).scaleY(1.2f).setDuration(150).withEndAction(() -> {
                fabLike.animate().scaleX(1f).scaleY(1f).setDuration(150).start();
            }).start();
        }
    }
}
