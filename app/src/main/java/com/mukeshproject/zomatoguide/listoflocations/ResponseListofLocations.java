package com.mukeshproject.zomatoguide.listoflocations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListofLocations {

    @SerializedName("has_total")
    private int hasTotal;

    @SerializedName("location_suggestions")
    private List<LocationSuggestionsItem> locationSuggestions;

    @SerializedName("has_more")
    private int hasMore;

    @SerializedName("user_has_addresses")
    private boolean userHasAddresses;

    @SerializedName("status")
    private String status;

    public int getHasTotal() {
        return hasTotal;
    }

    public List<LocationSuggestionsItem> getLocationSuggestions() {
        return locationSuggestions;
    }

    public int getHasMore() {
        return hasMore;
    }

    public boolean isUserHasAddresses() {
        return userHasAddresses;
    }

    public String getStatus() {
        return status;
    }
}