package com.mukeshproject.zomatoguide.listofrestaurants;

import com.google.gson.annotations.SerializedName;

public class HasMenuStatus {

    @SerializedName("delivery")
    private int delivery;

    @SerializedName("takeaway")
    private int takeaway;

    public int getDelivery() {
        return delivery;
    }

    public int getTakeaway() {
        return takeaway;
    }
}