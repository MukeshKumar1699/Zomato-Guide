package com.mukeshproject.zomatoguide.listofrestaurants;

import com.google.gson.annotations.SerializedName;
import com.mukeshproject.zomatoguide.R;

import java.util.List;

public class Restaurant {

    @SerializedName("include_bogo_offers")
    private boolean includeBogoOffers;

    @SerializedName("has_online_delivery")
    private int hasOnlineDelivery;

    @SerializedName("establishment_types")
    private List<Object> establishmentTypes;

    @SerializedName("has_table_booking")
    private int hasTableBooking;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("average_cost_for_two")
    private int averageCostForTwo;

    @SerializedName("menu_url")
    private String menuUrl;

    @SerializedName("price_range")
    private int priceRange;

    @SerializedName("switch_to_order_menu")
    private int switchToOrderMenu;

    @SerializedName("R")
    private com.mukeshproject.zomatoguide.R R;

    @SerializedName("all_reviews_count")
    private int allReviewsCount;

    @SerializedName("is_table_reservation_supported")
    private int isTableReservationSupported;

    @SerializedName("timings")
    private String timings;

    @SerializedName("currency")
    private String currency;

    @SerializedName("opentable_support")
    private int opentableSupport;

    @SerializedName("id")
    private String id;

    @SerializedName("all_reviews")
    private AllReviews allReviews;

    @SerializedName("user_rating")
    private UserRating userRating;

    @SerializedName("offers")
    private List<Object> offers;

    @SerializedName("apikey")
    private String apikey;

    @SerializedName("is_delivering_now")
    private int isDeliveringNow;

    @SerializedName("deeplink")
    private String deeplink;

    @SerializedName("is_zomato_book_res")
    private int isZomatoBookRes;

    @SerializedName("establishment")
    private List<String> establishment;

    @SerializedName("store_type")
    private String storeType;

    @SerializedName("featured_image")
    private String featuredImage;

    @SerializedName("medio_provider")
    private boolean medioProvider;

    @SerializedName("url")
    private String url;

    @SerializedName("cuisines")
    private String cuisines;

    @SerializedName("phone_numbers")
    private String phoneNumbers;

    @SerializedName("photo_count")
    private int photoCount;

    @SerializedName("highlights")
    private List<String> highlights;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private Location location;

    @SerializedName("book_again_url")
    private String bookAgainUrl;

    @SerializedName("is_book_form_web_view")
    private int isBookFormWebView;

    @SerializedName("book_form_web_view_url")
    private String bookFormWebViewUrl;

    @SerializedName("mezzo_provider")
    private String mezzoProvider;

    @SerializedName("photos_url")
    private String photosUrl;

    public boolean isIncludeBogoOffers() {
        return includeBogoOffers;
    }

    public int getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public List<Object> getEstablishmentTypes() {
        return establishmentTypes;
    }

    public int getHasTableBooking() {
        return hasTableBooking;
    }

    public String getThumb() {
        return thumb;
    }

    public int getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public int getSwitchToOrderMenu() {
        return switchToOrderMenu;
    }

    public R getR() {
        return R;
    }

    public int getAllReviewsCount() {
        return allReviewsCount;
    }

    public int getIsTableReservationSupported() {
        return isTableReservationSupported;
    }

    public String getTimings() {
        return timings;
    }

    public String getCurrency() {
        return currency;
    }

    public int getOpentableSupport() {
        return opentableSupport;
    }

    public String getId() {
        return id;
    }

    public AllReviews getAllReviews() {
        return allReviews;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public String getApikey() {
        return apikey;
    }

    public int getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public int getIsZomatoBookRes() {
        return isZomatoBookRes;
    }

    public List<String> getEstablishment() {
        return establishment;
    }

    public String getStoreType() {
        return storeType;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public boolean isMedioProvider() {
        return medioProvider;
    }

    public String getUrl() {
        return url;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getBookAgainUrl() {
        return bookAgainUrl;
    }

    public int getIsBookFormWebView() {
        return isBookFormWebView;
    }

    public String getBookFormWebViewUrl() {
        return bookFormWebViewUrl;
    }

    public String getMezzoProvider() {
        return mezzoProvider;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }
}