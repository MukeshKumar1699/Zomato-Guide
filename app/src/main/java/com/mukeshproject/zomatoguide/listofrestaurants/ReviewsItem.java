package com.mukeshproject.zomatoguide.listofrestaurants;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsItem {

    @SerializedName("review")
    private List<Object> review;

    public List<Object> getReview() {
        return review;
    }
}