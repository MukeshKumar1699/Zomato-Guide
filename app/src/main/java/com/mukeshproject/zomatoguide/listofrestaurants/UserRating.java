package com.mukeshproject.zomatoguide.listofrestaurants;

import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("aggregate_rating")
    private String aggregateRating;

    @SerializedName("rating_color")
    private String ratingColor;

    @SerializedName("rating_obj")
    private RatingObj ratingObj;

    @SerializedName("rating_text")
    private String ratingText;

    @SerializedName("votes")
    private int votes;

    public String getAggregateRating() {
        return aggregateRating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public RatingObj getRatingObj() {
        return ratingObj;
    }

    public String getRatingText() {
        return ratingText;
    }

    public int getVotes() {
        return votes;
    }
}