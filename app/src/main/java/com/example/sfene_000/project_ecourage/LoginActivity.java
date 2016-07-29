package com.example.sfene_000.project_ecourage;

import  android.app.Activity;
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

import com.example.sfene_000.project_ecourage.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.json.*;

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


        String[] params = new String[]{username, password};
        LogInUser signUpUser = new LogInUser(this);
        signUpUser.execute(params);
        //String passwordHash = encode(password);

       // Log.d("LOGINTEST:onClick", "encode(" + password + "): " + passwordHash);

       // if (loginSuccessful(username, password)) {
//            Log.d("LOGINTEST:onClick", "success!");
//            Intent intent = new Intent(view.getContext(), MainActivity.class);
//            setResult(Activity.RESULT_OK, intent);
//            startActivity(intent);
//        } else {
//            Log.d("LOGINTEST:onClick", "fail.");
//        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
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

    private class LogInUser extends AsyncTask<String, Void, String> {
        private Activity activity;
        public LogInUser(Activity activity){
            this.activity= activity;
        }
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            Random randomGenerator = new Random();
            if(username.length()>0 && password.length()>0){
                String url = "http://ecourage.org/sql_query.php?nFunction=2&username="+username+"&password="+password;
                URL obj = null;
                HttpURLConnection con = null;
                try {
                    obj = new URL(url);
                    con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.connect();
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject json = new JSONObject(response.toString());
                    Log.d("BUTT",json.get("message").toString());
                    String responseMessage = json.get("message").toString();
                    if(responseMessage.equals("log in successful")){
                        return "logged in";
                    } else{
                        return "wrong password";
                    }
                } catch (MalformedURLException e) {
                    return "error";
                } catch (IOException e) {
                    return "error";
                } catch (JSONException e) {
                    return "error";
                } finally {
                    con.disconnect();
                }

            }
            return "empty field";
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("empty field")){
                TextView error =(TextView) findViewById(R.id.error);
                error.setText("One or more fields is missing your information!");
            } else if(result.equals("wrong password")) {
                TextView error =(TextView) findViewById(R.id.error);
                error.setText("incorrect passcode");
            } else {
                Intent intent = new Intent(activity, MainActivity.class);
                setResult(Activity.RESULT_OK, intent);
                startActivity(intent);
            }
        }
    }

}
