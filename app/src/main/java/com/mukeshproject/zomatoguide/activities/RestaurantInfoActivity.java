package com.mukeshproject.zomatoguide.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.adapter.FragmentAdapterRestaurantInfo;

public class RestaurantInfoActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private final int res_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        initViews();
        setViewPagerAdapter();
    }


    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerRestaurantInfo);
        tabLayout = findViewById(R.id.tabLayoutRestaurantInfo);
    }

    private void setViewPagerAdapter() {

        FragmentAdapterRestaurantInfo fragmentAdapterRestaurantInfo = new FragmentAdapterRestaurantInfo(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mViewPager.setAdapter(fragmentAdapterRestaurantInfo);
        tabLayout.setupWithViewPager(mViewPager);
    }
}