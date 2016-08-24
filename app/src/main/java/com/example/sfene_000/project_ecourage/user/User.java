package com.example.sfene_000.project_ecourage.user;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sfene_000 on 7/29/2016.
 */
public class User implements Serializable {

    private String username;
    private boolean isCoach;
    private int coachCode;
    private String coachUsername;
    private boolean hasCoach;

    public User(String username, String coachUsername, String coachCode, boolean isCoach, boolean hasCoach) {
        this.username = username;
        this.coachUsername = coachUsername;
        this.coachCode = Integer.parseInt(coachCode);
        this.isCoach = isCoach;
        this.hasCoach = hasCoach;
    }

    public User(JSONObject user) throws JSONException {
        Log.d("USER.JAVA", user.get("coach_code").toString());
        this.username = user.get("username").toString();
        this.coachUsername = user.get("coach_username").toString();
        this.coachCode = Integer.parseInt(user.get("coach_code").toString());
        if(this.coachUsername.length()==0){
            this.hasCoach=false;
        } else{
            this.hasCoach=true;
        }
        if(user.get("coach_username").toString().length()==0){
            this.isCoach=false;
        }else{
            this.isCoach=true;
        }
        String responseMessage = user.get("username").toString();
    }

    public String getUsername() {
        return username;
    }
    public boolean isCoach() {
        return isCoach;
    }
    public boolean hasCoach() {
        return hasCoach;
    }
    public String getCoachUsername() {
        return coachUsername;
    }
    public int getCoachCode() {
        return coachCode;
    }

    public String findCoach(Context context, int coachCode) {
        DBHandler db = new DBHandler(context);
        return db.findCoach(coachCode);
    }

    public void setCoach(String coachUsername) {
        this.coachUsername = coachUsername;
    }


    public String toString() {
        return "{username:"+username+", isCoach:"+isCoach+", hasCoach:"+hasCoach+", coachUsername:"+coachUsername+", coachCode:"+coachCode+"}";
    }

}
