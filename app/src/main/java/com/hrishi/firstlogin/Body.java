package com.hrishi.firstlogin;

import com.google.gson.annotations.SerializedName;


class Body{
    String title;
    String body;
    String image;
    String username;

    public Body(String title, String body, String image,String username) {
        this.title = title;
        this.body = body;
        this.image = image;
        this.username=username;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImage() {
        return image;
    }
}
