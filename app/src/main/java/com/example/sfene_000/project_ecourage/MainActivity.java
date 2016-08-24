package com.example.sfene_000.project_ecourage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.sfene_000.project_ecourage.tools.BeYourOwnFriend.BeYourOwnFriendSlidersActivity;
import com.example.sfene_000.project_ecourage.tools.LiftYourMoodActivity;
import com.example.sfene_000.project_ecourage.tools.breathingActivity.breathingActivityHome;
import com.example.sfene_000.project_ecourage.user.DBHandler;
import com.example.sfene_000.project_ecourage.user.User;
import com.example.sfene_000.project_ecourage.user.invite_coach;

public class MainActivity extends AppCompatActivity {

    android.app.ActionBar actionBar;
    SessionManager session;
    DBHandler db;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = new SessionManager(this);

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


        db = new DBHandler(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        Log.d("name",prefs.getString("currentUser",""));
//        user = db.getUser("name");
//        if(user !=null){
//            Log.d("name",user.getUsername());
//        }

    }


    public void launchBreathing(View view) {
        Intent intent = new Intent(view.getContext(), breathingActivityHome.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchLiftYourMood(View view) {
        Intent intent = new Intent(view.getContext(), LiftYourMoodActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchGetSupport(View view) {
        Intent intent = new Intent(view.getContext(), LiftYourMoodActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchOwnFriend(View view) {
        Intent intent = new Intent(view.getContext(), BeYourOwnFriendSlidersActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void logout(View view) {
        session.logoutUser();
    }

    public void inviteCoach(View view){
        Intent intent = new Intent(view.getContext(), invite_coach.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }




}
