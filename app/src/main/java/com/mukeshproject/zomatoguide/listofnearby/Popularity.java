package com.mukeshproject.zomatoguide.listofnearby;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Popularity {

    @SerializedName("nightlife_index")
    private String nightlifeIndex;

    @SerializedName("city")
    private String city;

    @SerializedName("top_cuisines")
    private List<String> topCuisines;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("nearby_res")
    private List<String> nearbyRes;

    @SerializedName("nightlife_res")
    private String nightlifeRes;

    @SerializedName("subzone_id")
    private int subzoneId;

    @SerializedName("popularity_res")
    private String popularityRes;

    @SerializedName("subzone")
    private String subzone;

    public String getNightlifeIndex() {
        return nightlifeIndex;
    }

    public String getCity() {
        return city;
    }

    public List<String> getTopCuisines() {
        return topCuisines;
    }

    public String getPopularity() {
        return popularity;
    }

    public List<String> getNearbyRes() {
        return nearbyRes;
    }

    public String getNightlifeRes() {
        return nightlifeRes;
    }

    public int getSubzoneId() {
        return subzoneId;
    }

    public String getPopularityRes() {
        return popularityRes;
    }

    public String getSubzone() {
        return subzone;
    }
}