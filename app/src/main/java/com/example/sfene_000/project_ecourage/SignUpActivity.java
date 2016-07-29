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
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText newPasswordField;
    EditText confirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        newPasswordField = (EditText) findViewById(R.id.newPasswordField);
        newPasswordField.setTypeface(Typeface.DEFAULT);
        newPasswordField.setTransformationMethod(new PasswordTransformationMethod());
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        confirmPasswordField.setTypeface(Typeface.DEFAULT);
        confirmPasswordField.setTransformationMethod(new PasswordTransformationMethod());

    }

    public void signUp(View view) {

        String newPassword = newPasswordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();

        String username = "STUB";

        if (!newPassword.equals(confirmPassword)) {
            Log.d("SIGNUP:onClick", "password fields do not match!");
            return;
        }
        if (userExists(username)) {
            Log.d("SIGNUP:onClick", "username already taken!");
            return;
        }

        // newUser(email, username, password, isCoach);

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    // STUB IMPLEMENTATION
    private boolean userExists(String username) {
        return false;
    }
}
