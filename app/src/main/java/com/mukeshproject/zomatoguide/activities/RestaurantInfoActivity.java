package com.mukeshproject.zomatoguide.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.adapter.FragmentAdapterRestaurantInfo;
import com.mukeshproject.zomatoguide.listeners.FragmentCommunicationListener;

public class RestaurantInfoActivity extends AppCompatActivity implements FragmentCommunicationListener {

    Bundle bundle = new Bundle();
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private int res_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        initViews();
        getIntentData();
        setViewPagerAdapter();
    }

    private void getIntentData() {
        res_id = getIntent().getIntExtra("res_id", 0);
        bundle.putInt("res_id", res_id);
        displayToastMessage(String.valueOf(res_id));
    }

    private void setViewPagerAdapter() {

        FragmentAdapterRestaurantInfo fragmentAdapterRestaurantInfo = new FragmentAdapterRestaurantInfo(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle);

        mViewPager.setAdapter(fragmentAdapterRestaurantInfo);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerRestaurantInfo);
        tabLayout = findViewById(R.id.tabLayoutRestaurantInfo);


    }

    @Override
    public void onFragmentDataPassed(Bundle bundle, String launch) {

    }

    private void displayToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}