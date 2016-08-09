package com.example.sfene_000.project_ecourage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity {

    EditText newPasswordField;
    EditText confirmPasswordField;
    EditText emailField;
    EditText usernameField;
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

        emailField = (EditText) findViewById(R.id.emailField);

       usernameField = (EditText) findViewById(R.id.usernameField);

    }

    public void signUp(View view) {


        String newPassword = newPasswordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();

        String username = usernameField.getText().toString();;
        String email = emailField.getText().toString();


        if (!newPassword.equals(confirmPassword)) {
            setErrorMessage("Passwords don't match");
            return;
        }
        Log.d("password",encode(newPassword));
        String[] params = new String[]{username.toLowerCase(), encode(newPassword),email};
        SignUpUser signUpUser = new SignUpUser(this);
        signUpUser.execute(params);

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
    private void setErrorMessage(String errorMessage){
        TextView error =(TextView) findViewById(R.id.error);
        error.setText(errorMessage);
    }


    private class SignUpUser extends AsyncTask<String, Void, String> {
        private Activity activity;
        public SignUpUser(Activity activity){
            this.activity = activity;
        }
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String email = params[2];
            Random randomGenerator = new Random();
            if(username.length()>0 && password.length()>0 && email.length()>0){
                String url = "http://ecourage.org/sql_query.php?nFunction=4&username="+username+"&password="+password+"&coach_code="+ randomGenerator.nextInt(100000) +"&email="+email;
                String responseMessage = (new ConnectionManager(url)).getResponseMessage();
                if(responseMessage.equals("user successfully created")){
                    return "user created";
                } else{
                    return "user exists already";
                }
            }
            return "empty field";
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("empty field")){
                setErrorMessage("One or more fields is missing your information!");
            } else if(result.equals("user exists already")) {
                setErrorMessage("that username is already taken. Try another!");
            } else if(result.equals("error")) {
                setErrorMessage("Something went wrong on our end :( Try again Later");
            } else {
                Intent intent = new Intent(activity, MainActivity.class);
                setResult(Activity.RESULT_OK, intent);
                startActivity(intent);
                finish();
            }
        }


    }

}
