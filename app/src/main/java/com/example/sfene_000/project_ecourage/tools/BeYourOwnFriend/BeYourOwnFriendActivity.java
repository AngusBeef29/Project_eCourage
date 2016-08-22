package com.example.sfene_000.project_ecourage.tools.BeYourOwnFriend;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sfene_000.project_ecourage.R;

import java.util.ArrayList;

public class BeYourOwnFriendActivity extends ListActivity implements MultiSpinner.MultiSpinnerListener {

    ArrayList<String> thoughtsList = new ArrayList<String>();
    ArrayList<String> resultsList = new ArrayList<String>();
    ArrayList<String> displayList = new ArrayList<String>();
    ArrayList<String> customThoughtList = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    EditText customThoughtField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byof_main);

        // Spinner element
        MultiSpinner spinner = (MultiSpinner) findViewById(R.id.byof_spinner);

        thoughtsList.add("Automobile");
        thoughtsList.add("Business Services");
        thoughtsList.add("Computers");
        thoughtsList.add("Education");
        thoughtsList.add("Personal");
        thoughtsList.add("Travel");

        spinner.setItems(thoughtsList, "Thoughts", this);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                displayList);

        setListAdapter(adapter);

        customThoughtField = (EditText)findViewById(R.id.custom_thought_field);
    }

    public void submitThoughts(View view) {

        Intent intent = new Intent(BeYourOwnFriendActivity.this, BeYourOwnFriendConfirmThoughtsActivity.class);
        intent.putExtra("displayList", displayList);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);

    }

    @Override
    public void onItemsSelected(boolean[] selected) {

        displayList.clear();
        replaceCustomThoughts();

        resultsList.clear();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                resultsList.add(thoughtsList.get(i));
            }
        }

        for (String result : resultsList) {
            if (!displayList.contains(result))
                displayList.add(result);
        }

        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Selected: " + resultsList.toString(), Toast.LENGTH_LONG).show();
    }

    public void addCustomThought(View v) {

        String customThought = customThoughtField.getText().toString();
        // if the custom field is not empty
        if (!customThought.equals("") && !displayList.contains(customThought)) {
            displayList.add(customThought);
            customThoughtList.add(customThought);
            adapter.notifyDataSetChanged();
        }
    }

    public void replaceCustomThoughts() {
        displayList.addAll(customThoughtList);
    }
}
