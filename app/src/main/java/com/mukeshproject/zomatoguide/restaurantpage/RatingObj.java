package com.mukeshproject.zomatoguide.restaurantpage;

import com.google.gson.annotations.SerializedName;

public class RatingObj {

    @SerializedName("bg_color")
    private BgColor bgColor;

    @SerializedName("title")
    private Title title;

    public BgColor getBgColor() {
        return bgColor;
    }

    public Title getTitle() {
        return title;
    }
}