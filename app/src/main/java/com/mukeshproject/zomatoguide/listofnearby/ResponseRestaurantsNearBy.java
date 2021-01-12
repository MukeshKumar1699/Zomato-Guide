package com.mukeshproject.zomatoguide.listofnearby;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRestaurantsNearBy {

    @SerializedName("popularity")
    private Popularity popularity;

    @SerializedName("link")
    private String link;

    @SerializedName("nearby_restaurants")
    private List<NearbyRestaurantsItem> nearbyRestaurants;

    @SerializedName("location")
    private Location location;

    public Popularity getPopularity() {
        return popularity;
    }

    public String getLink() {
        return link;
    }

    public List<NearbyRestaurantsItem> getNearbyRestaurants() {
        return nearbyRestaurants;
    }

    public Location getLocation() {
        return location;
    }
}