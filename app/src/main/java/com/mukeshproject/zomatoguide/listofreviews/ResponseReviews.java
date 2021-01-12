package com.mukeshproject.zomatoguide.listofreviews;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseReviews {

    @SerializedName("reviews_start")
    private int reviewsStart;

    @SerializedName("user_reviews")
    private List<UserReviewsItem> userReviews;

    @SerializedName("reviews_shown")
    private int reviewsShown;

    @SerializedName("reviews_count")
    private int reviewsCount;

    public int getReviewsStart() {
        return reviewsStart;
    }

    public List<UserReviewsItem> getUserReviews() {
        return userReviews;
    }

    public int getReviewsShown() {
        return reviewsShown;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }
}