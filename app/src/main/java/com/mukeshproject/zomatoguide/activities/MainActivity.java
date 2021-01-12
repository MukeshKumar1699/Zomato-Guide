package com.mukeshproject.zomatoguide.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.mukeshproject.zomatoguide.listeners.LooperPreparedListener;
import com.mukeshproject.zomatoguide.listoflocations.ResponseListofLocations;
import com.mukeshproject.zomatoguide.model.BackgroundThread;
import com.mukeshproject.zomatoguide.model.SmallDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LooperPreparedListener {

    private static final int LOCATION_PERMISSION_REQ_CODE = 102;
    private final SmallDatabase smallDatabase = new SmallDatabase();
    private final Bundle bundle = new Bundle();
    private Button btn_location;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private FusedLocationProviderClient client;
    private double lattitude = 0, longitude = 0;
    private int entity_id = 0;
    private String q = "";
    private boolean isLooperPrepared;
    private BackgroundThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = LocationServices.getFusedLocationProviderClient(this);
        initViewsandListeners();
        getIntentData();
//        if(isLooperPrepared) {
//            thread.addTaskToQueue(new Runnable() {
//                @Override
//                public void run() {
//                    fetchServer();
//                }
//            });
//        }
        fetchServer();
        setViewPagerAdapter();
    }

    private void initViewsandListeners() {

        btn_location = findViewById(R.id.btn_location);
        btn_location.setOnClickListener(this);

        mViewPager = findViewById(R.id.viewPagerMainActivity);
        tabLayout = findViewById(R.id.tabLayoutMainActivity);

        thread = new BackgroundThread("dataBase_Thread", this);
        thread.start();

    }


    private void getIntentData() {

        if (getIntent().getDoubleExtra("lat", 0) != 0 && getIntent().getDoubleExtra("long", 0) != 0) {
            lattitude = getIntent().getDoubleExtra("lat", 0);
            longitude = getIntent().getDoubleExtra("long", 0);
        } else if (getIntent().getStringExtra("city") != null && getIntent().getIntExtra("res_id", 0) != 0) {
            q = getIntent().getStringExtra("city");
            btn_location.setText(q);
            entity_id = getIntent().getIntExtra("entity_id", 0);
            bundle.putInt("entity_id", entity_id);
            bundle.putString("City", q);
        }
    }

    private void fetchServer() {
        ApiClient apiClient = Network.getRetrofitInstance().create(ApiClient.class);
        Call<ResponseListofLocations> call = apiClient.getLocations(q, lattitude, longitude, "f372e6f364b53f71036e6f5662ecfa99");

        call.enqueue(new Callback<ResponseListofLocations>() {
            @Override
            public void onResponse(Call<ResponseListofLocations> call, Response<ResponseListofLocations> response) {

                ResponseListofLocations responseListofLocations = response.body();
                smallDatabase.setEntity_id(responseListofLocations.getLocationSuggestions().get(0).getId());
                entity_id = responseListofLocations.getLocationSuggestions().get(0).getId();
                q = responseListofLocations.getLocationSuggestions().get(0).getName();
                btn_location.setText(q);
                bundle.putInt("entity_id", entity_id);
                bundle.putString("city", q);
            }

            @Override
            public void onFailure(Call<ResponseListofLocations> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViewPagerAdapter() {

        FragmentAdapterMainActivity fragmentAdapterMainActivity = new FragmentAdapterMainActivity(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle);
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
                            displayToastMessage("Location Permission already granted");

                            client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {

                                    if (location != null) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                                        intent.putExtra("lat", location.getLatitude());
                                        intent.putExtra("long", location.getLongitude());

                                        smallDatabase.setLattitude(location.getLatitude());
                                        smallDatabase.setLongitute(location.getLongitude());
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case LOCATION_PERMISSION_REQ_CODE:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    displayToastMessage("Location permission granted");
                } else {
                    displayToastMessage("Location permission denied");
                }
                break;
        }
    }

    private void displayToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLooperPrepared() {
        isLooperPrepared = true;
    }
}