package com.example.sfene_000.project_ecourage.tools.breathingActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sfene_000.project_ecourage.R;

public class breathingActivityHome extends AppCompatActivity {
    boolean popUpsPresent =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        if( sharedPref.getBoolean("openedBreathing",false) ){
            popUpsPresent=true;
        }
    }

    public void launchBreathingActivityOne(View view) {
        Intent intent = new Intent(view.getContext(), breathingActivity1.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchBreathingActivityTwo(View view) {
        Intent intent = new Intent(view.getContext(), breathingActivity2.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchBreathingActivityThree(View view) {
        Intent intent = new Intent(view.getContext(), breathingActivity3.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public void launchBreathingActivityInfo(View view) {
        Intent intent = new Intent(view.getContext(), breathingActivityInfo.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }
}
