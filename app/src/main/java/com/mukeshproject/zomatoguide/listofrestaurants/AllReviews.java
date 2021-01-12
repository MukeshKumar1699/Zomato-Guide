package com.mukeshproject.zomatoguide.listofrestaurants;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllReviews {

    @SerializedName("reviews")
    private List<ReviewsItem> reviews;

    public List<ReviewsItem> getReviews() {
        return reviews;
    }
}