package com.example.sfene_000.project_ecourage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class breathingActivity extends AppCompatActivity {
    boolean popUpsPresent =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);


        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        if( sharedPref.getBoolean("openedBreathing",false) ){
            popUpsPresent=true;
        }

        //SharedPreferences.Editor editor = sharedPref.edit();

    }
}
