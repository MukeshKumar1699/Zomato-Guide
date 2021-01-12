package com.mukeshproject.zomatoguide.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mukeshproject.zomatoguide.fragments.GoOutFragment;
import com.mukeshproject.zomatoguide.fragments.OrderFragment;
import com.mukeshproject.zomatoguide.fragments.ProfileFragment;

public class FragmentAdapterMainActivity extends FragmentStatePagerAdapter {

    private final Bundle bundle;

    public FragmentAdapterMainActivity(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:

                return OrderFragment.newInstance("", "");
            case 1:
                return GoOutFragment.newInstance("", "");
            case 2:
                return ProfileFragment.newInstance("", "");
        }
        return OrderFragment.newInstance("This is First Fragment", "");
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle = "";

        switch (position) {
            case 0:
                tabTitle = "Order";
                break;
            case 1:
                tabTitle = "Go Out";
                break;
            case 2:
                tabTitle = "Profile";
                break;
            default:
                tabTitle = "TAB - n";
                break;
        }
        return tabTitle;
    }
}
