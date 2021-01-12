package com.mukeshproject.zomatoguide.restaurantpage;

import com.google.gson.annotations.SerializedName;

public class R {

    @SerializedName("has_menu_status")
    private HasMenuStatus hasMenuStatus;

    @SerializedName("res_id")
    private int resId;

    @SerializedName("is_grocery_store")
    private boolean isGroceryStore;

    public HasMenuStatus getHasMenuStatus() {
        return hasMenuStatus;
    }

    public int getResId() {
        return resId;
    }

    public boolean isIsGroceryStore() {
        return isGroceryStore;
    }
}