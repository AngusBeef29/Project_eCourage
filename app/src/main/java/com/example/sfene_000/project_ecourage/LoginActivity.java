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
import android.widget.TextView;

import com.example.sfene_000.project_ecourage.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = (EditText)findViewById(R.id.usernameField);
        passwordField = (EditText)findViewById(R.id.passwordField);
        passwordField.setTypeface(Typeface.DEFAULT);
        passwordField.setTransformationMethod(new PasswordTransformationMethod());
    }


    public void login(View view) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        String passwordHash = encode(password);
        Log.d("LOGINTEST:onClick", "encode(" + password + "): " + passwordHash);

        if (loginSuccessful(username, password)) {
            Log.d("LOGINTEST:onClick", "success!");
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            setResult(Activity.RESULT_OK, intent);
            startActivity(intent);
        } else {
            Log.d("LOGINTEST:onClick", "fail.");
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    private boolean loginSuccessful(String username, String password) {
        if (userExists(username.toString()) && passwordMatch(password.toString())) {
            Log.d("LOGINTEST:logic", "username and password match!");
            return true;
        } else {
            Log.d("LOGINTEST:logic", "username/password do not match.");
            return false;
        }
    }

    //STUB IMPLEMENTATION
    private boolean userExists(String usernameString) {
        return false;
    }

    //STUB IMPLEMENTATION
    private boolean passwordMatch(String passwordHash) {
        return false;
    }

    private static String encode(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = md.digest(passwordBytes);
            String hash = hexToString(hashBytes);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff; // remove higher bits, sign
            if (val < 16)
                buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
}
