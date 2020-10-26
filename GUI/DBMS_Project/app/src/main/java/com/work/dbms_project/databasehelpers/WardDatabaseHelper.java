package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.work.dbms_project.usermodels.WardUserModel;

import java.util.ArrayList;

public class WardDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project_ward";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_WARD = "Ward";
    private static final String KEY_WARD_NO = "Ward_no";
    private static final String KEY_WARD_NAME = "ward_name";
    private static final String KEY_BLOCK = "block";
    private static final String KEY_NO_OF_BEDS = "no_of_beds";
 

    /*CREATE TABLE ward ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_WARD = "CREATE TABLE "
            + TABLE_WARD + "(" + KEY_WARD_NO
            + " TEXT PRIMARY KEY," + KEY_WARD_NAME + " TEXT,"+KEY_BLOCK + " TEXT,"+ KEY_NO_OF_BEDS + " TEXT);";

    public WardDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_WARD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_WARD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WARD + "'");
        onCreate(db);
    }

    public void addUser(String number, String ward_name,String block, String no_of_beds) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_WARD_NO,number);
        values.put(KEY_WARD_NAME,ward_name);
        values.put(KEY_BLOCK,block);
        values.put(KEY_NO_OF_BEDS,no_of_beds);
        
        long id = db.insertWithOnConflict(TABLE_WARD, null, values, SQLiteDatabase.CONFLICT_IGNORE);


        Log.d("The values",String.valueOf(id)+" "+String.valueOf(id));
    }

    public ArrayList<WardUserModel> getAllUsers() {
        ArrayList<WardUserModel> userModelArrayList = new ArrayList<WardUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_WARD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                WardUserModel userModel = new WardUserModel();
                String k=c.getString(c.getColumnIndex(KEY_WARD_NO));
                userModel.setWard_no(k);
                userModel.setBlock(c.getString(c.getColumnIndex(KEY_BLOCK)));
                userModel.setNo_of_beds(c.getString(c.getColumnIndex(KEY_NO_OF_BEDS)));
                userModel.setWard_name(c.getString(c.getColumnIndex(KEY_WARD_NAME)));

                // adding to Ward list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }
    public WardUserModel getResult(String id) {
        WardUserModel userModelArrayList = new WardUserModel();

        String selectQuery = "SELECT  * FROM " + TABLE_WARD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                WardUserModel userModel = new WardUserModel();
                String k=c.getString(c.getColumnIndex(KEY_WARD_NO));
                if(k.equals(id)) {
                    userModel.setWard_no(k);
                    userModel.setBlock(c.getString(c.getColumnIndex(KEY_BLOCK)));
                    userModel.setNo_of_beds(c.getString(c.getColumnIndex(KEY_NO_OF_BEDS)));
                    userModel.setWard_no(c.getString(c.getColumnIndex(KEY_WARD_NAME)));

                    // adding to Ward list
                    return userModel;
                }
            } while (c.moveToNext());
        }
        return null;
    }

    //    public void upblockUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_BLOCK, name);
//        db.upblock(TABLE_USER, values, KEY_WARD_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.upblock(TABLE_USER_HOBBY, valuesHobby, KEY_WARD_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.upblock(TABLE_USER_CITY, valuesCity, KEY_WARD_NO + " = ?", new String[]{String.valueOf(id)});
//    }
//
    public void deleteUSer(String id) {

        // delete row in ward table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_WARD, KEY_WARD_NO + " = ?",new String[]{String.valueOf(id)});

    }
}
