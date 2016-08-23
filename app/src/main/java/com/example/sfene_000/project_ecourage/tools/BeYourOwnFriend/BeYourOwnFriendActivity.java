package com.example.sfene_000.project_ecourage.tools.BeYourOwnFriend;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sfene_000.project_ecourage.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BeYourOwnFriendActivity extends ListActivity implements MultiSpinner.MultiSpinnerListener {

    ArrayList<String> resourceArguments = new ArrayList<>();        // built-in arguments
    ArrayList<String> customArguments = new ArrayList<>();          // custom arguments

    ArrayList<String> selectedArguments = new ArrayList<>();        // arguments selected from multiSpinner
    HashSet<Integer> selectedArgumentIndexes = new HashSet<>();
    ArrayList<String> display = new ArrayList<>();                // display of all arguments

    ArrayAdapter<String> adapter;
    EditText customArgumentField;

    View submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byof_main);

        submitButton = findViewById(R.id.byof_submit_thoughts);
        submitButton.setVisibility(View.GONE);

        // Spinner element
        MultiSpinner spinner = (MultiSpinner) findViewById(R.id.byof_spinner);

        // Places resource-based arguments in arguments list
        initArguments();
        spinner.setItems(resourceArguments, "Thoughts", this);

        customArgumentField = (EditText) findViewById(R.id.custom_thought_field);

        // initializes the result/display list
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                display);
        setListAdapter(adapter);
    }

    private void initArguments() {
        Resources res = getResources();
        TypedArray resourceArgumentArr = res.obtainTypedArray(R.array.arguments);
        for (int i = 0; i < resourceArgumentArr.length(); i++) {
            String argument = resourceArgumentArr.getString(i);
            resourceArguments.add(argument);
        }
    }

    private void addCustomThought(View v) {

        return;
//        TODO: Implement
//        String customThought = "";
//
//        if (customArgumentField.getText() != null) {
//            customThought = customArgumentField.getText().toString();
//        }
//
//        // if the custom field is not empty and unique
//        if (!customThought.equals("") && !display.contains(customThought)) {
//            display.add(customThought);
//            customArguments.add(customThought);
//            adapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

        // clears display of prior selectedArguments
        display.clear();
        selectedArguments.clear();
        selectedArgumentIndexes.clear();

        // re-adds customArguments
        display.addAll(customArguments);

        // extracts selectedArguments from all arguments
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                selectedArguments.add(resourceArguments.get(i));
                selectedArgumentIndexes.add(i);
            }
        }

        for (String selectedArgument : selectedArguments) {
            display.add(selectedArgument);
        }

        if (!selectedArguments.isEmpty()) {
            submitButton.setVisibility(View.VISIBLE);
        } else {
            submitButton.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();

    }

    private HashMap<String, ArrayList<String>> initCounterArgumentMap() {

        HashMap<String, ArrayList<String>> counterArgumentMap = new HashMap<String, ArrayList<String>>();
        Resources res = getResources();
        String packageName = getPackageName();

        if (selectedArgumentIndexes.contains(5) && selectedArgumentIndexes.contains(3)) {
            selectedArgumentIndexes.remove(5);
        }

        // pairs each counterArgument list to its resourceArgument (if argument has been selected)
        for (int i = 0; i < resourceArguments.size(); i++) {

            if (selectedArgumentIndexes.contains(i)) {
                // each counterArgument array is named after its corresponding argument's index
                int resId = res.getIdentifier("ca" + i, "array", packageName);
                ArrayList<String> counterArguments = new ArrayList<String>(Arrays.asList(res.getStringArray(resId)));
                counterArgumentMap.put(resourceArguments.get(i), counterArguments);
            }
        }

        return counterArgumentMap;
    }

    public void submitThoughts(View view) {

        if (selectedArguments.isEmpty()) {
            Toast.makeText(this, "Please select one or more arguments.", Toast.LENGTH_LONG).show();
            return;
        }

        HashMap<String, ArrayList<String>> counterArgumentMap = initCounterArgumentMap();
        Intent intent = new Intent(BeYourOwnFriendActivity.this, BeYourOwnFriendConfirmThoughtsActivity.class);
        intent.putExtra("counterArgumentMap", counterArgumentMap);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);

    }
}
