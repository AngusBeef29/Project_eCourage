package com.example.sfene_000.project_ecourage;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAdapter  extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "UserPage", "Home", "Tools" };
    private int TOTAL_TABS = 3;

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        switch (index) {
            case 0:
                return new UserPage();

            case 1:
                return new Home();

            case 2:
                return new Tools();
        }

        return null;
    }

    @Override
    public int getCount() {

        return TOTAL_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}