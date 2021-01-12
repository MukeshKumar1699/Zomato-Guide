package com.mukeshproject.zomatoguide.listofnearby;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("entity_type")
    private String entityType;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("country_name")
    private String countryName;

    @SerializedName("entity_id")
    private int entityId;

    @SerializedName("title")
    private String title;

    @SerializedName("country_id")
    private int countryId;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("zipcode")
    private String zipcode;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("locality_verbose")
    private String localityVerbose;

    @SerializedName("locality")
    private String locality;

    public String getCityName() {
        return cityName;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getEntityId() {
        return entityId;
    }

    public String getTitle() {
        return title;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getCityId() {
        return cityId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getLocalityVerbose() {
        return localityVerbose;
    }

    public String getLocality() {
        return locality;
    }
}