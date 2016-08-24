package com.example.sfene_000.project_ecourage.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sfene_000.project_ecourage.user.User;

import org.json.JSONException;

/**
 * Created by geoffreyangus on 8/8/16.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "localUserInfo";
    // Contacts table name
    private static final String USER = "user";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IS_COACH = "is_coach";
    private static final String KEY_HAS_COACH = "has_coach";
    private static final String KEY_COACH_NAME = "coach_name";
    private static final String KEY_COACH_CODE = "coach_code";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_IS_COACH + " INTEGER,"
                + KEY_HAS_COACH + " TEXT," + KEY_COACH_NAME + " TEXT," + KEY_COACH_CODE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USER);
// Creating tables again
        onCreate(db);
    }

    //returns true if
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername()); // username
        values.put(KEY_IS_COACH, user.isCoach() ? 1 : 0); //
        values.put(KEY_HAS_COACH, user.hasCoach() ? 1 : 0); // isCoach
        values.put(KEY_COACH_NAME, user.getCoachUsername()); // username
        values.put(KEY_COACH_CODE, user.getCoachCode()); // username
        // Inserting Row
        db.insert(USER, null, values);
        db.close(); // Closing database connection
    }

    // Getting one shop
    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE name=?", new String[]{username});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User contact = null;
        Log.d("DBHANDLER", cursor.getString(1));
        String name = cursor.getString(1);
        String coachName = cursor.getString(4);
        String coachCode = cursor.getString(5);
        boolean isCoach = cursor.getString(2).equals("1");
        boolean hasCoach = cursor.getString(3).equals("1");
        contact = new User(name, coachName, coachCode, isCoach, hasCoach);
        return contact;
    }

    //STUB IMPLEMENTATION (close to what you need).
    //TODO: make this work with PHP SQL, not local SQL
    public String findCoach(int coachCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM user WHERE coach_code=?", new String[] {coachCode+""});
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return null;
    }

}
