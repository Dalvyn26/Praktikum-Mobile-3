package com.example.tp3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddBookFragment extends Fragment {

    private TextInputEditText etTitle, etAuthor, etYear, etGenre, etBlurb;
    private ImageView ivPreview;
    private View placeholderImage, cardImagePicker;
    private MaterialButton btnSubmit;
    private Uri selectedImageUri = null;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    Glide.with(this).load(selectedImageUri).into(ivPreview);
                    placeholderImage.setVisibility(View.GONE);
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTitle = view.findViewById(R.id.etTitle);
        etAuthor = view.findViewById(R.id.etAuthor);
        etYear = view.findViewById(R.id.etYear);
        etGenre = view.findViewById(R.id.etGenre);
        etBlurb = view.findViewById(R.id.etBlurb);
        ivPreview = view.findViewById(R.id.ivPreview);
        placeholderImage = view.findViewById(R.id.placeholderImage);
        cardImagePicker = view.findViewById(R.id.cardImagePicker);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        cardImagePicker.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        btnSubmit.setOnClickListener(v -> submitBook());
    }

    private void submitBook() {
        String title = etTitle.getText().toString().trim();
        String author = etAuthor.getText().toString().trim();
        String yearStr = etYear.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String blurb = etBlurb.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() || genre.isEmpty() || blurb.isEmpty()) {
            Toast.makeText(getContext(), "Mohon isi semua data", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImageUri == null) {
            Toast.makeText(getContext(), "Mohon pilih cover buku", Toast.LENGTH_SHORT).show();
            return;
        }

        int year = Integer.parseInt(yearStr);
        String imagePath = selectedImageUri.toString();

        Book newBook = new Book(title, author, year, blurb, imagePath, false, 0.0f, genre);
        DataDummy.addBook(newBook);

        Toast.makeText(getContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();

        // Clear form
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etGenre.setText("");
        etBlurb.setText("");
        ivPreview.setImageDrawable(null);
        placeholderImage.setVisibility(View.VISIBLE);
        selectedImageUri = null;
        
        // Return to Home
        if (getActivity() instanceof MainActivity) {
            com.google.android.material.bottomnavigation.BottomNavigationView bnv = getActivity().findViewById(R.id.bottom_navigation);
            if (bnv != null) {
                bnv.setSelectedItemId(R.id.nav_home);
            }
        }
    }
}
