package com.mukeshproject.zomatoguide.listofrestaurants;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListOfRestaurants {

    @SerializedName("results_found")
    private int resultsFound;

    @SerializedName("results_shown")
    private int resultsShown;

    @SerializedName("restaurants")
    private List<RestaurantsItem> restaurants;

    @SerializedName("results_start")
    private int resultsStart;

    public int getResultsFound() {
        return resultsFound;
    }

    public int getResultsShown() {
        return resultsShown;
    }

    public List<RestaurantsItem> getRestaurants() {
        return restaurants;
    }

    public int getResultsStart() {
        return resultsStart;
    }
}