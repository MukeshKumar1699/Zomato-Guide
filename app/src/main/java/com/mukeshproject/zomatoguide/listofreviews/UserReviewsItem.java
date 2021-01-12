package com.mukeshproject.zomatoguide.listofreviews;

import com.google.gson.annotations.SerializedName;

public class UserReviewsItem {

    @SerializedName("review")
    private Review review;

    public Review getReview() {
        return review;
    }
}