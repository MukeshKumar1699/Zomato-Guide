package com.mukeshproject.zomatoguide.listofnearby;

import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }
}