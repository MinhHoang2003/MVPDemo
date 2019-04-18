package com.example.mvpdemologinsreen.model.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mvpdemologinsreen.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper implements UserDAO {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int VERSION = 1;
    private static UserDatabase sInstance;
    private static String CREATE_TABLE_QUERY = "CREATE TABLE " + UserEntry.TABLE_NAME + " ("
            + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserEntry._USERNAME + " TEXT NOT NULL,"
            + UserEntry._PASSWORD + " TEXT NOT NULL" + " );";

    private Cursor mCursor;
    private SQLiteDatabase mSQLiteDatabase;

    private UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static UserDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserDatabase(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> listUsers = new ArrayList<>();
        mSQLiteDatabase = getReadableDatabase();
        mCursor = mSQLiteDatabase.query(UserEntry.TABLE_NAME, null, null,
                null, null, null, null);
        if (mCursor.moveToFirst()) {
            do {
                User user = new User(mCursor.getString(mCursor.getColumnIndex(UserEntry._USERNAME)),
                        mCursor.getString(mCursor.getColumnIndex(UserEntry._PASSWORD)));
                listUsers.add(user);
            } while (mCursor.moveToNext());
        }
        mSQLiteDatabase.close();
        mCursor.close();
        return listUsers;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        mSQLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry._USERNAME, user.getEmail());
        values.put(UserEntry._PASSWORD, user.getPassword());
        long result = mSQLiteDatabase.insert(UserEntry.TABLE_NAME, null, values);
        mSQLiteDatabase.close();
        return result >= 0;
    }

    @Override
    public boolean isValidUser(User user) {
        mSQLiteDatabase = getReadableDatabase();
        String selection = UserEntry._USERNAME + " = ? AND " + UserEntry._PASSWORD + " = ?";
        String[] selectionArg = {user.getEmail(), user.getPassword()};
        mCursor = mSQLiteDatabase.query(UserEntry.TABLE_NAME, null, selection, selectionArg, null, null, null);
        if (mCursor.getCount() > 0) {
            mSQLiteDatabase.close();
            mCursor.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean hasEmail(User user) {
        mSQLiteDatabase = getReadableDatabase();
        String selection = UserEntry._USERNAME + " = ?";
        String[] selectionArg = {user.getEmail()};
        mCursor = mSQLiteDatabase.query(UserEntry.TABLE_NAME, null, selection, selectionArg, null, null, null);

        if (mCursor.getCount() > 0) {
            mSQLiteDatabase.close();
            mCursor.close();
            return true;
        }
        return false;
    }
}
