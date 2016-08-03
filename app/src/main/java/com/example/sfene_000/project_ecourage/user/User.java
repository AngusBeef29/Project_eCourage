package com.example.sfene_000.project_ecourage.user;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sfene_000 on 7/29/2016.
 */
public class User {
    private String username;
    private Boolean isCoach;
    private int coachCode;
    private String coachUsername;
    private Boolean hasCoach;

    public User(JSONObject user) throws JSONException {
        this.username = user.get("username").toString();
        this.coachUsername = user.get("coach_username").toString();
        this.coachCode = Integer.getInteger(user.get("coach_code").toString());
        if(this.coachUsername.length()==0){
            this.hasCoach=false;
        } else{
            this.hasCoach=true;
        }
        if(Integer.getInteger(user.get("coach_username").toString())==0){
            this.isCoach=false;
        }else{
            this.isCoach=true;
        }
        String responseMessage = user.get("username").toString();
    }

}
