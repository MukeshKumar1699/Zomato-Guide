package com.mukeshproject.zomatoguide.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mukeshproject.zomatoguide.fragments.MenuFragment;
import com.mukeshproject.zomatoguide.fragments.OverviewFragment;
import com.mukeshproject.zomatoguide.fragments.PhotosFragment;
import com.mukeshproject.zomatoguide.fragments.ReviewsFragment;

public class FragmentAdapterRestaurantInfo extends FragmentStatePagerAdapter {

    public FragmentAdapterRestaurantInfo(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OverviewFragment();
            case 1:
                return new MenuFragment();
            case 2:
                return new PhotosFragment();
            case 3:
                return new ReviewsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle = "";

        switch (position) {
            case 0:
                tabTitle = "Overview";
                break;
            case 1:
                tabTitle = "Menu";
                break;
            case 2:
                tabTitle = "Photos";
                break;
            case 3:
                tabTitle = "Reviews";
                break;
            default:
                tabTitle = "TAB - n";
                break;
        }
        return tabTitle;
    }
}
