package com.mukeshproject.zomatoguide;

import com.mukeshproject.zomatoguide.listofrestaurants.RestaurantsItem;

import java.util.Comparator;

public class CustomComparator implements Comparator<RestaurantsItem> {
    @Override
    public int compare(RestaurantsItem o1, RestaurantsItem o2) {
        return o1.getRestaurant().getName().compareTo(o2.getRestaurant().getName());
    }

}
