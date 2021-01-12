package com.mukeshproject.zomatoguide.restaurantpage;

import com.google.gson.annotations.SerializedName;

public class BgColor {

    @SerializedName("type")
    private String type;

    @SerializedName("tint")
    private String tint;

    public String getType() {
        return type;
    }

    public String getTint() {
        return tint;
    }
}