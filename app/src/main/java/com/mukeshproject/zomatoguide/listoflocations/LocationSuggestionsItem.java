package com.mukeshproject.zomatoguide.listoflocations;

import com.google.gson.annotations.SerializedName;

public class LocationSuggestionsItem {

    @SerializedName("should_experiment_with")
    private int shouldExperimentWith;

    @SerializedName("has_new_ad_format")
    private int hasNewAdFormat;

    @SerializedName("has_go_out_tab")
    private int hasGoOutTab;

    @SerializedName("discovery_enabled")
    private int discoveryEnabled;

    @SerializedName("is_state")
    private int isState;

    @SerializedName("state_name")
    private String stateName;

    @SerializedName("name")
    private String name;

    @SerializedName("country_name")
    private String countryName;

    @SerializedName("country_flag_url")
    private String countryFlagUrl;

    @SerializedName("id")
    private int id;

    @SerializedName("state_id")
    private int stateId;

    @SerializedName("state_code")
    private String stateCode;

    @SerializedName("country_id")
    private int countryId;

    public int getShouldExperimentWith() {
        return shouldExperimentWith;
    }

    public int getHasNewAdFormat() {
        return hasNewAdFormat;
    }

    public int getHasGoOutTab() {
        return hasGoOutTab;
    }

    public int getDiscoveryEnabled() {
        return discoveryEnabled;
    }

    public int getIsState() {
        return isState;
    }

    public String getStateName() {
        return stateName;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryFlagUrl() {
        return countryFlagUrl;
    }

    public int getId() {
        return id;
    }

    public int getStateId() {
        return stateId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public int getCountryId() {
        return countryId;
    }
}