package com.example.sfene_000.project_ecourage.tools.BeYourOwnFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;

import com.example.sfene_000.project_ecourage.R;

import java.util.ArrayList;

/**
 * Created by geoffreyangus on 8/21/16.
 */
public class BeYourOwnFriendConfirmThoughtsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byof_confirm_thoughts);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<String> resultsList = extras.getStringArrayList("displayList");
            Log.d("BYOF", resultsList.toString());
        }
    }

}
