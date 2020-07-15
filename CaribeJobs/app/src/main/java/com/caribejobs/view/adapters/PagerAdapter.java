package com.caribejobs.view.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.caribejobs.R;
import com.caribejobs.view.fragments.ProfileFragment;
import com.caribejobs.view.fragments.SearchFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private final int PROFILE = 0;
    private final int SEARCH = 1;
    private Context context;

    @StringRes
    private static final int[] TAB_TITLES = new int[] { R.string.tab_text_1, R.string.tab_text_2 };

    public PagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case PROFILE:
                return new ProfileFragment();
            case SEARCH:
                return new SearchFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }
}
