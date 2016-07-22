package com.example.sfene_000.project_ecourage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sfene_000.project_ecourage.R;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.usernameField);
        password = (EditText)findViewById(R.id.passwordField);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    public void login(View view) {
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
            Log.d("LOGINTEST", "success!");
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            setResult(Activity.RESULT_OK, intent);
            startActivity(intent);
        } else {
            Log.d("LOGINTEST", "fail.");
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }
}
