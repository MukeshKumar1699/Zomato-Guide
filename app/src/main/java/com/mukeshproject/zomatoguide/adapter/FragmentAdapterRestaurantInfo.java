package com.mukeshproject.zomatoguide.adapter;

import android.os.Bundle;

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

    private final Bundle bundle;

    public FragmentAdapterRestaurantInfo(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
        this.bundle = bundle;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OverviewFragment overviewFragment = new OverviewFragment();
                overviewFragment.setArguments(bundle);
                return overviewFragment;
            case 1:
                MenuFragment menuFragment = new MenuFragment();
                menuFragment.setArguments(bundle);
                return menuFragment;
            case 2:
                PhotosFragment photosFragment = new PhotosFragment();
                photosFragment.setArguments(bundle);
                return photosFragment;
            case 3:
                ReviewsFragment reviewsFragment = new ReviewsFragment();
                reviewsFragment.setArguments(bundle);
                return reviewsFragment;
        }
        return OverviewFragment.newInstance("This is Default Fragment", "");
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
