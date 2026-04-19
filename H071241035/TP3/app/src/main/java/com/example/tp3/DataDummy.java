package com.example.tp3;

import java.util.ArrayList;

public class DataDummy {

    public static ArrayList<Book> books = new ArrayList<>();

    public static void initializeData() {
        if (!books.isEmpty()) return;

        books.add(new Book("Laskar Pelangi", "Andrea Hirata", 2005, 
            "Kisah inspiratif sepuluh anak pesisir Belitung yang berjuang meraih pendidikan di tengah kemiskinan.", 
            "https://simpus.mkri.id/uploaded_files/sampul_koleksi/original/Monograf//uploadedfiles/perpustakaan/11610-11613.jpg", false, 4.8f, "Pendidikan"));

        books.add(new Book("Bumi Manusia", "Pramoedya Ananta Toer", 1980, 
            "Perjalanan Minke, seorang pribumi cerdas di era kolonial Belanda yang memperjuangkan keadilan dan cinta.", 
            "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjDzhJMSDGL9nlNqbJm0S7bxponHgTgH61KCISo9zwJynHvfJXDXTfIhiHJU-2QtIMS25sTW_qq_5b9q8MmjK83XbjSkBmrii_vLV8f5ZdMTswyAVa_rlDmXNxAQrII-lyhrk8Kt8WBTcY/s1600/9789799731234.jpg", false, 4.9f, "Sastra Klasik"));

        books.add(new Book("Laut Bercerita", "Leila S. Chudori", 2017, 
            "Kisah persahabatan, cinta, dan pengkhianatan di kalangan aktivis mahasiswa menjelang reformasi 1998.", 
            "https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1516602134i/36393774.jpg", false, 4.7f, "Sejarah"));

        books.add(new Book("Atomic Habits", "James Clear", 2018, 
            "Panduan praktis untuk membangun kebiasaan baik dan menghilangkan kebiasaan buruk melalui perubahan-perubahan kecil.", 
            "https://image.gramedia.net/rs:fit:0:0/plain/https://cdn.gramedia.com/uploads/items/9786020633176_.Atomic_Habit.jpg", false, 4.8f, "Self-Improvement"));

        books.add(new Book("Filosofi Kopi", "Dee Lestari", 2006, 
            "Dua sahabat yang membangun kedai kopi dan mencari racikan kopi paling sempurna yang mengubah hidup mereka.", 
            "https://cdn.gramedia.com/uploads/items/Filosofi_Kopi_Kump.CeritaProsa_Satu_Dekade.jpg", false, 4.5f, "Fiksi Lontar"));

        books.add(new Book("Sapiens", "Yuval Noah Harari", 2011, 
            "Eksplorasi mendalam tentang sejarah spesies manusia, dari zaman batu hingga abad ke-21.", 
            "https://cdn.gramedia.com/uploads/items/591701404_sapiens.jpg", false, 4.6f, "Sejarah"));

        books.add(new Book("Hujan", "Tere Liye", 2016, 
            "Kisah fiksi ilmiah tentang persahabatan, cinta, dan melupakan di tengah bencana alam dahsyat di masa depan.", 
            "https://cdn.gramedia.com/uploads/items/9786020324784_Hujan-Cover-Baru-2018.jpg", false, 4.4f, "Sci-Fi"));

        books.add(new Book("Psychology of Money", "Morgan Housel", 2020, 
            "Pelajaran abadi mengenai kekayaan, ketamakan, dan kebahagiaan dalam mengelola keuangan pribadi.", 
            "https://mojokstore.com/wp-content/uploads/2021/08/The-Psychology-of-Money.jpg", false, 4.7f, "Keuangan"));

        books.add(new Book("Cantik Itu Luka", "Eka Kurniawan", 2002, 
            "Epos magis-realis tentang sejarah Indonesia yang kelam melalui kisah seorang perempuan dan keturunannya.", 
            "https://cdn.gramedia.com/uploads/items/9786020366517_Cantik-Itu-Luka-Hard-Cover---Limited-Edition.jpg", false, 4.5f, "Sastra"));

        books.add(new Book("1984", "George Orwell", 1949, 
            "Novel distopia klasik tentang bahaya totaliterisme, pengawasan massal, dan represi kebebasan berpikir.", 
            "https://cdn.gramedia.com/uploads/items/1984_cov.jpg", false, 4.9f, "Misteri / Sci-Fi"));

        books.add(new Book("Dilan 1990", "Pidi Baiq", 2014, 
            "Romansa masa SMA di Bandung tahun 90-an yang manis, kocak, dan penuh kenangan.", 
            "https://cdn.gramedia.com/uploads/items/9786027870864_dilan-1990.jpg", false, 4.2f, "Romansa"));

        books.add(new Book("Bodo Amat", "Mark Manson", 2016, 
            "Pendekatan yang berlawanan dengan intuisi untuk menjalani kehidupan yang lebih baik dengan mengenali batasan diri.", 
            "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Sebuah-seni-untuk-bersikap-bodoh-amat.jpg/250px-Sebuah-seni-untuk-bersikap-bodoh-amat.jpg", false, 4.3f, "Self-Improvement"));

        books.add(new Book("Pulang", "Leila S. Chudori", 2012, 
            "Drama keluarga dan politik tentang eksil politik Indonesia di Paris yang merindukan tanah airnya.", 
            "https://cdn.gramedia.com/uploads/items/9786024242756_Pulang-New-C.jpg", false, 4.6f, "Sejarah"));

        books.add(new Book("Perahu Kertas", "Dee Lestari", 2009, 
            "Kisah pasang surut hubungan dua manusia yang memiliki mimpi terpendam dan cinta yang tak terucap.", 
            "https://cdn.gramedia.com/uploads/items/ID_MIZ2016MTH03PKER_C.jpg", false, 4.4f, "Romansa"));

        books.add(new Book("Bumi", "Tere Liye", 2014, 
            "Petualangan fantasi remaja yang menemukan dunia paralel tersembunyi dengan teknologi dan kekuatan luar biasa.", 
            "https://cdn.gramedia.com/uploads/items/9786020332956_Bumi-New-Cover.jpg", false, 4.5f, "Fantasi"));
    }

    public static void addBook(Book book) {
        books.add(0, book); // Tambahkan ke paling atas (terbaru)
    }

    public static ArrayList<Book> getAllBooks() {
        return books;
    }
}