package com.mukeshproject.zomatoguide.listofreviews;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("rating_color")
    private String ratingColor;

    @SerializedName("review_time_friendly")
    private String reviewTimeFriendly;

    @SerializedName("rating_text")
    private String ratingText;

    @SerializedName("comments_count")
    private int commentsCount;

    @SerializedName("rating")
    private int rating;

    @SerializedName("review_text")
    private String reviewText;

    @SerializedName("id")
    private int id;

    @SerializedName("user")
    private User user;

    @SerializedName("timestamp")
    private int timestamp;

    @SerializedName("likes")
    private int likes;

    public String getRatingColor() {
        return ratingColor;
    }

    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    public String getRatingText() {
        return ratingText;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }
}