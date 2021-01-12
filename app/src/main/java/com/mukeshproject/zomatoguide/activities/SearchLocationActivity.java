package com.mukeshproject.zomatoguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.adapter.ListOfLocations_Rc_Adapter;
import com.mukeshproject.zomatoguide.api.ApiClient;
import com.mukeshproject.zomatoguide.api.Network;
import com.mukeshproject.zomatoguide.listeners.ItemClickListenerSearchLocation;
import com.mukeshproject.zomatoguide.listoflocations.LocationSuggestionsItem;
import com.mukeshproject.zomatoguide.listoflocations.ResponseListofLocations;
import com.mukeshproject.zomatoguide.model.SmallDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchLocationActivity extends AppCompatActivity implements ItemClickListenerSearchLocation {

    private final SmallDatabase smallDatabase = new SmallDatabase();
    private String location = "";
    private ResponseListofLocations responseListofLocations;
    private RecyclerView rc_Location;
    private SearchView sv_searchForLocation_SearchLocation;
    private ImageView iv_back_searchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        initViews();
        getIntentData();

    }

    private void initViews() {
        iv_back_searchLocation = findViewById(R.id.iv_back_reviewFrag);
        sv_searchForLocation_SearchLocation = findViewById(R.id.sv_searchForLocation_SearchLocation);
        rc_Location = findViewById(R.id.rc_Location);
    }

    private void getIntentData() {
        if (getIntent().getStringExtra("location") != null) {
            location = getIntent().getStringExtra("location");
            fetchServer(location);
        }
    }

    private void fetchServer(String location) {
        ApiClient apiClient = Network.getRetrofitInstance().create(ApiClient.class);

        Call<ResponseListofLocations> call = apiClient.getLocations(location, 0.0, 0.0, "f372e6f364b53f71036e6f5662ecfa99");
        call.enqueue(new Callback<ResponseListofLocations>() {
            @Override
            public void onResponse(Call<ResponseListofLocations> call, Response<ResponseListofLocations> response) {

                responseListofLocations = response.body();
                setRecyclerAdapter(responseListofLocations);
            }

            @Override
            public void onFailure(Call<ResponseListofLocations> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerAdapter(ResponseListofLocations responseListofLocations) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchLocationActivity.this);
        rc_Location.setLayoutManager(layoutManager);

        ListOfLocations_Rc_Adapter rcLocation_adapter = new ListOfLocations_Rc_Adapter(responseListofLocations.getLocationSuggestions(), this);
        rc_Location.setAdapter(rcLocation_adapter);
    }

    @Override
    public void onItemClick(int position, LocationSuggestionsItem locationSuggestionsItem) {

        Intent intent = new Intent(SearchLocationActivity.this, MainActivity.class);

        smallDatabase.setCityName(locationSuggestionsItem.getName());
        smallDatabase.setRes_id(locationSuggestionsItem.getId());

        intent.putExtra("res_id", locationSuggestionsItem.getId());
        intent.putExtra("city", locationSuggestionsItem.getName());
        startActivity(intent);
        finish();
    }
}