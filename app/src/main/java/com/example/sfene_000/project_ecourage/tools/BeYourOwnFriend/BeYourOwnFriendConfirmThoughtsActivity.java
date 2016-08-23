package com.example.sfene_000.project_ecourage.tools.BeYourOwnFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sfene_000.project_ecourage.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by geoffreyangus on 8/21/16.
 * ListView credits to: http://amitandroid.blogspot.com/2013/03/android-listview-with-checkbox-and.html
 */
public class BeYourOwnFriendConfirmThoughtsActivity extends AppCompatActivity {

    HashMap<String, ArrayList<String>> counterArgumentMap = new HashMap<String, ArrayList<String>>();
    ArrayList<BYOFCounterArgument> counterArgumentList = new ArrayList<>();

    BYOFChecklistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byof_confirm_thoughts);

        Bundle extras = getIntent().getExtras();
        counterArgumentMap = (HashMap<String, ArrayList<String>>) extras.getSerializable("counterArgumentMap");

        for (String argument : counterArgumentMap.keySet()) {
            ArrayList<String> counterArguments = counterArgumentMap.get(argument);
            for (String counterArgumentString : counterArguments) {
                BYOFCounterArgument counterArgument = new BYOFCounterArgument(counterArgumentString, false);
                counterArgumentList.add(counterArgument);
            }
        }

        adapter = new BYOFChecklistAdapter(this, counterArgumentList);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(adapter);

    }

    public void showResult(View v) {
        String result = "Selected counterarguments are:";
        int totalAmount=0;
        for (BYOFCounterArgument ca : adapter.getCheckedBoxes()) {
            if (ca.checkBox){
                result += " " + ca.counterArgument;
            }
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

}
