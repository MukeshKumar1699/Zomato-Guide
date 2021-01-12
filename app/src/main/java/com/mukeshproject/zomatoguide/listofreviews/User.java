package com.mukeshproject.zomatoguide.listofreviews;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("zomato_handle")
    private String zomatoHandle;

    @SerializedName("profile_deeplink")
    private String profileDeeplink;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("profile_url")
    private String profileUrl;

    @SerializedName("foodie_color")
    private String foodieColor;

    @SerializedName("name")
    private String name;

    public String getZomatoHandle() {
        return zomatoHandle;
    }

    public String getProfileDeeplink() {
        return profileDeeplink;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getFoodieColor() {
        return foodieColor;
    }

    public String getName() {
        return name;
    }
}