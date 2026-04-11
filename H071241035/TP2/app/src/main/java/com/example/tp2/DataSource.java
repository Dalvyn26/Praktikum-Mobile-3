package com.example.tp2;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Post> feedPosts = new ArrayList<>();
    public static List<Story> allStories = new ArrayList<>();
    public static User mainUser = new User("dalvyn_suhada", "Dalvyn Suhada", R.drawable.profil_main);

    static {
        User u1 = new User("user1", "User Satu", R.drawable.profil_1);
        User u2 = new User("user2", "User Dua", R.drawable.profil_2);
        User u3 = new User("user3", "User Tiga", R.drawable.profil_3);

        // Feed Posts (Minimal 10)
        feedPosts.add(new Post(u1, R.drawable.feed_1, "INAUGURASI 2024"));
        feedPosts.add(new Post(u1, R.drawable.feed_2, "Naik Bebek-bebek Kitaa"));
        feedPosts.add(new Post(u2, R.drawable.feed_3, "Tarian Daerah"));
        feedPosts.add(new Post(u2, R.drawable.feed_4, "Jadi MC DULU"));
        feedPosts.add(new Post(u3, R.drawable.feed_5, "Okee Sihhh..."));
        feedPosts.add(new Post(u3, R.drawable.feed_6, "Berdoa Dulu GEs"));
        feedPosts.add(new Post(mainUser, R.drawable.feed_7, "Upacara Kali Yak"));
        feedPosts.add(new Post(mainUser, R.drawable.feed_8, "PKKMB"));
        feedPosts.add(new Post(mainUser, R.drawable.feed_9, "Otw Idul Adha"));
        feedPosts.add(new Post(mainUser, R.drawable.feed_10, "Final project."));

        // Stories for Main User
        allStories.add(new Story(mainUser, R.drawable.story_1, "Life"));
        allStories.add(new Story(mainUser, R.drawable.story_2, "Ba Lomba"));
        allStories.add(new Story(mainUser, R.drawable.story_3, "Panci Ege"));
        allStories.add(new Story(mainUser, R.drawable.story_4, "Panci 2"));
        allStories.add(new Story(mainUser, R.drawable.story_5, "Jamaah..."));
        allStories.add(new Story(mainUser, R.drawable.story_6, "Pengukuhan"));
        allStories.add(new Story(mainUser, R.drawable.story_7, "Family"));

        // Stories for User 1
        allStories.add(new Story(u1, R.drawable.story_1, "Day 1"));
        allStories.add(new Story(u1, R.drawable.story_2, "Day 2"));
        allStories.add(new Story(u1, R.drawable.story_8, "Rombongan Dulss"));

        // Stories for User 2
        allStories.add(new Story(u2, R.drawable.story_3, "Cooking"));
        allStories.add(new Story(u2, R.drawable.story_4, "Cat"));
    }

    public static List<Post> getPostsByUser(String username) {
        List<Post> userPosts = new ArrayList<>();
        for (Post post : feedPosts) {
            if (post.getUser().getUsername().equals(username)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    public static List<Story> getStoriesByUser(String username) {
        List<Story> userStories = new ArrayList<>();
        for (Story story : allStories) {
            if (story.getUser().getUsername().equals(username)) {
                userStories.add(story);
            }
        }
        return userStories;
    }
}
