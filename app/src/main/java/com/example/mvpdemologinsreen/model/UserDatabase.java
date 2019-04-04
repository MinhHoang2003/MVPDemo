package com.example.mvpdemologinsreen.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {
    private static UserDatabase sInstance;
    public static final String TAG = "MVP: UserDatabase";
    public static final String DATABASE_NAME = "UserDatabase";
    public static final String TABLE_NAME = "User";
    public static final String _USERNAME = "user";
    public static final String _PASSWORD = "password";
    public static final String _ID = "id";
    public static final int VERSION = 1;
    private static String QUERY = "CREATE TABLE "+TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + _USERNAME + " TEXT NOT NULL,"
            + _PASSWORD + " TEXT NOT NULL"+" );";

    private UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        db.execSQL(QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public static UserDatabase getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new UserDatabase(context);
            return sInstance;
        }
        else return sInstance;
    }
    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_USERNAME,user.getEmail());
        values.put(_PASSWORD,user.getPassword());
        db.insert(TABLE_NAME,null,values);
        Log.d(TAG, "addUser: ");
        db.close();
    }
    public ArrayList<String> getListEmail(){
        ArrayList<String> listEmail = new ArrayList<>();
        String QueryGetEmail = "SELECT "+_USERNAME + " FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryGetEmail,null);
        if(cursor.moveToFirst()){
            do{
                listEmail.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }

        return listEmail;
    }
    public ArrayList<User> getListUser(){
        ArrayList<User> listUsers = new ArrayList<>();
        String QueryGetEmail = "SELECT *" + " FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryGetEmail,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User(cursor.getString(1),
                        cursor.getString(2));
                listUsers.add(user);
            }while(cursor.moveToNext());
        }

        return listUsers;
    }


}
