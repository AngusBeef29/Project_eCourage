package com.example.sfene_000.project_ecourage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText newPassword;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        newPassword = (EditText)findViewById(R.id.newPasswordField);
        newPassword.setTypeface(Typeface.DEFAULT);
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        confirmPassword = (EditText)findViewById(R.id.confirmPasswordField);
        confirmPassword.setTypeface(Typeface.DEFAULT);
        confirmPassword.setTransformationMethod(new PasswordTransformationMethod());
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

    public void signUp(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }
}
