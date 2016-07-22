package com.example.sfene_000.project_ecourage.tools;

import  android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.sfene_000.project_ecourage.R;
import com.example.sfene_000.project_ecourage.tools.LiftYourMoodFragments.*;
import com.viewpagerindicator.CirclePageIndicator;


public class LiftYourMoodActivity extends FragmentActivity {

    private static final int NUM_PAGES = 7;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_your_mood);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        CirclePageIndicator circleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
        circleIndicator.setViewPager(mPager);
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new tools_lym_p1();
                case 1:
                    return new tools_lym_p2();
                case 2:
                    return new tools_lym_p3();
                case 3:
                    return new tools_lym_p4();
                case 4:
                    return new tools_lym_p5();
                case 5:
                    return new tools_lym_p6();
                case 6:
                    return new tools_lym_p7();
                case 7:
                    return new tools_lym_p8();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

