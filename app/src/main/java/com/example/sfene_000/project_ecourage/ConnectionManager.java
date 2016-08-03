package com.example.sfene_000.project_ecourage;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sfene_000 on 8/3/2016.
 */
public class ConnectionManager {
    private String url;
    private String responseMessage;
    JSONObject jsonResponse;

    public ConnectionManager(String url){
        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            responseMessage = processResponse(con);
        } catch (MalformedURLException e) {
            responseMessage = "error";
        } catch (IOException e) {
            responseMessage = "error";
        } finally {
            con.disconnect();
        }
    }

    public JSONObject getJSONObj(){
        return jsonResponse;
    }

    public String getResponseMessage(){
        return responseMessage;
    }

    private String processResponse(HttpURLConnection con){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            jsonResponse = new JSONObject(response.toString());
            String responseMessage = jsonResponse.get("message").toString();
            return responseMessage;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


}
