package com.mukeshproject.zomatoguide.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.adapter.FragmentAdapterMainActivity;
import com.mukeshproject.zomatoguide.api.ApiClient;
import com.mukeshproject.zomatoguide.api.Network;
import com.mukeshproject.zomatoguide.listoflocations.ResponseListofLocations;
import com.mukeshproject.zomatoguide.sharedpreferences.PreferenceHelper;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PREF_LATITUDE_KEY = "PREF_LATITUDE_KEY";
    private static final String PREF_LONGITUDE_KEY = "PREF_LONGITUDE_KEY";
    private static final String PREF_CITY_NAME_KEY = "PREF_CITY_NAME_KEY";
    private static final String PREF_ENTITY_ID_KEY = "PREF_ENTITY_ID_KEY";

    private static final int LOCATION_PERMISSION_REQ_CODE = 102;
    private Button btn_location;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private FusedLocationProviderClient client;
    private double latitude = 0, longitude = 0;
    private int entity_id = 0;
    private String q = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = LocationServices.getFusedLocationProviderClient(this);

        initViewsandListeners();
        getPrefData();

        fetchServer();
        setViewPagerAdapter();
        upDateUI();
    }

    private void upDateUI() {

        btn_location.setText(q);
    }


    private void initViewsandListeners() {

        btn_location = findViewById(R.id.btn_location);
        btn_location.setOnClickListener(this);

        mViewPager = findViewById(R.id.viewPagerMainActivity);
        tabLayout = findViewById(R.id.tabLayoutMainActivity);
    }

    private void getPrefData() {

        latitude = PreferenceHelper.getLatitudeFromPreference(MainActivity.this, PREF_LATITUDE_KEY);
        longitude = PreferenceHelper.getLongitudeFromPreference(MainActivity.this, PREF_LONGITUDE_KEY);
        q = PreferenceHelper.getCityNameFromPreference(MainActivity.this, PREF_CITY_NAME_KEY);
        entity_id = PreferenceHelper.getEntityIdFromPreference(MainActivity.this, PREF_ENTITY_ID_KEY);

    }

    private void fetchServer() {
        ApiClient apiClient = Network.getRetrofitInstance().create(ApiClient.class);
        Call<ResponseListofLocations> call = apiClient.getLocations("", latitude, longitude, "f372e6f364b53f71036e6f5662ecfa99");

        call.enqueue(new Callback<ResponseListofLocations>() {
            @Override
            public void onResponse(@NotNull Call<ResponseListofLocations> call, @NotNull Response<ResponseListofLocations> response) {

                ResponseListofLocations responseListofLocations = response.body();

                entity_id = responseListofLocations.getLocationSuggestions().get(0).getId();
                PreferenceHelper.writeEntityIdToPreference(MainActivity.this, PREF_ENTITY_ID_KEY, entity_id);

                q = responseListofLocations.getLocationSuggestions().get(0).getName();
                PreferenceHelper.writeCityNameToPreference(MainActivity.this, PREF_CITY_NAME_KEY, q);

            }

            @Override
            public void onFailure(@NotNull Call<ResponseListofLocations> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViewPagerAdapter() {
        FragmentAdapterMainActivity fragmentAdapterMainActivity = new FragmentAdapterMainActivity(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(fragmentAdapterMainActivity);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.btn_location:

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet, findViewById(R.id.bottomSheetContainer));

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                ImageView iv_close = bottomSheetView.findViewById(R.id.iv_close);
                iv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                SearchView sv_searchForLocation = bottomSheetView.findViewById(R.id.sv_searchForLocation);
                sv_searchForLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        if (query != null) {

                            Intent intent = new Intent(getApplicationContext(), SearchLocationActivity.class);
                            intent.putExtra("location", query);
                            startActivity(intent);
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

                TextView tv_gps = bottomSheetView.findViewById(R.id.tv_gps);
                tv_gps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isPermissionGranted = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                        if (!isPermissionGranted) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQ_CODE);
                        } else {

                            client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {

                                    if (location != null) {

                                        latitude = location.getLatitude();
                                        longitude = location.getLongitude();

                                        PreferenceHelper.writeLatitudeToPreference(MainActivity.this, PREF_LATITUDE_KEY, latitude);
                                        PreferenceHelper.writeLongitudeToPreference(MainActivity.this, PREF_LONGITUDE_KEY, longitude);
                                        finish();
                                    }
                                }

                            });
                        }
                    }
                });
        }
    }

}