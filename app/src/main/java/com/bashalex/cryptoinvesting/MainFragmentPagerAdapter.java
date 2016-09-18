package com.bashalex.cryptoinvesting;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsFragment;
import com.bashalex.cryptoinvesting.NewsScreen.NewsFragment;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsFragment;
import com.bashalex.cryptoinvesting.TicketsScreen.TicketsFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    int PAGE_COUNT;
    private String tabTitles[];

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.tabTitles = context.getResources().getStringArray(R.array.main_tabs_names);
        this.PAGE_COUNT = this.tabTitles.length;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AltCoinsFragment.newInstance();
            case 1:
                return TicketsFragment.newInstance();
            case 2:
                return NewsFragment.newInstance();
            default:
                return NotificationsFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}