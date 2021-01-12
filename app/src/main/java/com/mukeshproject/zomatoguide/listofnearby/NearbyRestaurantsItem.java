package com.mukeshproject.zomatoguide.listofnearby;

import com.google.gson.annotations.SerializedName;

public class NearbyRestaurantsItem {

    @SerializedName("restaurant")
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }
}