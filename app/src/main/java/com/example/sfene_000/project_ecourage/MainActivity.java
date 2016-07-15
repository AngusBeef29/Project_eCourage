package com.example.sfene_000.project_ecourage;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    android.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getActionBar();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(),
                MainActivity.this));
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();
    }


    public void launchBreathing(View view) {
        Intent intent = new Intent(view.getContext(), breathingActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchLiftYourMood(View view) {
        Intent intent = new Intent(view.getContext(), LiftYourMoodActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

}
