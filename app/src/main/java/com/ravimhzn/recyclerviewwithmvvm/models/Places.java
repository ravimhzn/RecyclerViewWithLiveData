package com.ravimhzn.recyclerviewwithmvvm.models;

public class Places {
    private String title;
    private String imageUrl;

    public Places(String imageUrl, String title) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
