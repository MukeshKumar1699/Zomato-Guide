package com.mukeshproject.zomatoguide.api;

import com.mukeshproject.zomatoguide.listoflocations.ResponseListofLocations;
import com.mukeshproject.zomatoguide.listofrestaurants.ResponseListOfRestaurants;
import com.mukeshproject.zomatoguide.listofreviews.ResponseReviews;
import com.mukeshproject.zomatoguide.restaurantpage.ResponseRestaurantInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiClient {

    @GET("api/v2.1/cities")
    Call<ResponseListofLocations> getLocations(@Query("q") String q,
                                               @Query("lat") Double lat,
                                               @Query("lon") Double lon,
                                               @Header("user-key") String key);

    @GET("api/v2.1/geocode")
    Call<ResponseListofLocations> getRestaurantsNearBy(@Query("lat") Double lat,
                                                       @Query("lon") Double lon,
                                                       @Header("user-key") String key);

    @GET("/api/v2.1/search")
    Call<ResponseListOfRestaurants> getRestaurantSearchResponse(@Query("q") String q,
                                                                @Query("entity_id") int entity_id,
                                                                @Query("entity_type") String entity_type,
                                                                @Header("user-key") String key);

    @GET("/api/v2.1/restaurant")
    Call<ResponseRestaurantInfo> getRestaurantPageInfo(@Query("res_id") int res_id,
                                                       @Header("user-key") String key);

    @GET("/api/v2.1/reviews")
    Call<ResponseReviews> getRestaurantReviews(@Query("res_id") int res_id,
                                               @Header("user-key") String key);

}
