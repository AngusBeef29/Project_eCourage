package com.example.sfene_000.project_ecourage.user;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import  android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sfene_000.project_ecourage.ConnectionManager;
import com.example.sfene_000.project_ecourage.MainActivity;
import com.example.sfene_000.project_ecourage.R;

import org.json.JSONException;

public class invite_coach extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_coach);
    }

    public void sendInvite(View view){

    }


    private class LogInUser extends AsyncTask<String, Void, String> {
        private Activity activity;

        public LogInUser(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            if (username.length() > 0 && password.length() > 0) {
                String url = "http://ecourage.org/sql_query.php?nFunction=2&username=" + username + "&password=" + password;
                ConnectionManager connectionManager = new ConnectionManager(url);
                Log.d("password", url);
                String responseMessage = (connectionManager.getResponseMessage());
                Log.d("password", responseMessage);

            }
            return "empty field";
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("empty field")) {
                TextView error = (TextView) findViewById(R.id.error);
                error.setText("One or more fields is missing your information!");
            } else if (result.equals("wrong password")) {
                TextView error = (TextView) findViewById(R.id.error);
                error.setText("incorrect passcode");
            } else {
                Intent intent = new Intent(activity, MainActivity.class);
//                intent.putExtra("CurrentUser", user);
                setResult(Activity.RESULT_OK, intent);
                startActivity(intent);
                finish();
            }
        }

    }
}
