package com.example.mynotebook;

import java.util.ArrayList;

public class HomeList {

    private int imageId;
    private String title;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<HomeList> getData() {

        ArrayList<HomeList> dataList = new ArrayList<HomeList>();

        int[] images = {R.drawable.note_icon, R.drawable.date_icon, R.drawable.poi_icon, R.drawable.movie_icon};

        for (int i = 0; i < images.length; i++) {

            HomeList h = new HomeList();
            h.setImageId(images[i]);
            if (i == 0) h.setTitle("My Notes");
            else if (i == 1) h.setTitle("My Dates");
            else if (i == 2) h.setTitle("My Locations");
            else h.setTitle("My Movies");

            dataList.add(h);
        }

        return dataList;
    }
}
