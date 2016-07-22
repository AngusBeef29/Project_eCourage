package com.example.sfene_000.project_ecourage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SliderManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public SliderManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("first", 0);
        editor = pref.edit();
    }

    public void setFirst(boolean isFirst) {
        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    public boolean Check() {
        return pref.getBoolean("check", true);
    }
}
