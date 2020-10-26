package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.work.dbms_project.usermodels.RequistionUserModel;

import java.util.ArrayList;

public class RequistionDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project_requi";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_REQUISTION = "Requistion";
    private static final String KEY_REQUISTION_NO = "Requistion_no";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_DATE_OF_ORDER = "date_of_order";
    private static final String KEY_DATE_RECEIVED = "date_received";
    private static final String KEY_WARD_NO = "ward_no";
    private static final String KEY_STAFF_ID = "staff_id";
    private static final String KEY_DRUG_ID = "drug_id";

    /*CREATE TABLE requistion ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_REQUISTION = "CREATE TABLE "
            + TABLE_REQUISTION + "(" + KEY_REQUISTION_NO
            + " TEXT PRIMARY KEY," + KEY_QUANTITY + " TEXT,"+KEY_DATE_OF_ORDER + " TEXT,"+ KEY_DATE_RECEIVED + " TEXT,"+ KEY_WARD_NO + " TEXT,"+KEY_STAFF_ID +  " TEXT,"
            +KEY_DRUG_ID +  " TEXT, FOREIGN KEY("+KEY_DRUG_ID +") REFERENCES "+ DrugDatabaseHelper.TABLE_DRUG+"("+KEY_DRUG_ID+"),"+
        " FOREIGN KEY("+KEY_STAFF_ID+") REFERENCES "+ StaffDatabaseHelper.TABLE_STAFF+"("+KEY_STAFF_ID+"),"+
    "FOREIGN KEY("+KEY_WARD_NO +") REFERENCES "+ WardDatabaseHelper.TABLE_WARD+"("+KEY_WARD_NO+"));";

    public RequistionDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        con=context;
        Log.d("table", CREATE_TABLE_REQUISTION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REQUISTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_REQUISTION + "'");
        onCreate(db);
    }

    public void addUser(String number, String quantity,String date_of_order, String date_received,String ward_no,String staff_id,String drug_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_REQUISTION_NO,number);
        values.put(KEY_QUANTITY,quantity);
        values.put(KEY_DATE_OF_ORDER,date_of_order);
        values.put(KEY_DATE_RECEIVED,date_received);

        values.put(KEY_WARD_NO,ward_no);
        values.put(KEY_STAFF_ID,staff_id);
        values.put(KEY_DRUG_ID,drug_id);
        long id = db.insertWithOnConflict(TABLE_REQUISTION, null, values, SQLiteDatabase.CONFLICT_IGNORE);


        Log.d("The values",String.valueOf(id)+" "+String.valueOf(id));
    }

    public ArrayList<RequistionUserModel> getAllUsers() {
        ArrayList<RequistionUserModel> userModelArrayList = new ArrayList<RequistionUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_REQUISTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                RequistionUserModel userModel = new RequistionUserModel();
                String k=c.getString(c.getColumnIndex(KEY_REQUISTION_NO));
                userModel.setRequistion_no(k);
                userModel.setDate_of_order(c.getString(c.getColumnIndex(KEY_DATE_OF_ORDER)));
                userModel.setDate_received(c.getString(c.getColumnIndex(KEY_DATE_RECEIVED)));
                userModel.setWard_no(c.getString(c.getColumnIndex(KEY_WARD_NO)));
                userModel.setStaff_id(c.getString(c.getColumnIndex(KEY_STAFF_ID)));
                userModel.setDrug_id(c.getString(c.getColumnIndex(KEY_DRUG_ID)));
                userModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                Toast.makeText(con, c.getString(c.getColumnIndex(KEY_DRUG_ID)), Toast.LENGTH_SHORT).show();
                // adding to Requistion list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public RequistionUserModel getResult(String id) {

        String selectQuery = "SELECT  * FROM " + TABLE_REQUISTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                RequistionUserModel userModel = new RequistionUserModel();
                String k=c.getString(c.getColumnIndex(KEY_REQUISTION_NO));
                if(k.equals(id)) {
                    userModel.setRequistion_no(k);
                    userModel.setDate_of_order(c.getString(c.getColumnIndex(KEY_DATE_OF_ORDER)));
                    userModel.setDate_received(c.getString(c.getColumnIndex(KEY_DATE_RECEIVED)));
                    userModel.setWard_no(c.getString(c.getColumnIndex(KEY_WARD_NO)));
                    userModel.setStaff_id(c.getString(c.getColumnIndex(KEY_STAFF_ID)));
                    userModel.setDrug_id(c.getString(c.getColumnIndex(KEY_DRUG_ID)));
                    userModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                    // adding to Requistion list
                    return userModel;
                }
            } while (c.moveToNext());
        }
        return null;
    }
    //    public void update_of_orderUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_DATE_OF_ORDER, name);
//        db.update_of_order(TABLE_USER, values, KEY_REQUISTION_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.update_of_order(TABLE_USER_HOBBY, valuesHobby, KEY_REQUISTION_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.update_of_order(TABLE_USER_CITY, valuesCity, KEY_REQUISTION_NO + " = ?", new String[]{String.valueOf(id)});
//    }
//
    Context con;
    public void deleteUSer(String id) {

        // delete row in requistion table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_REQUISTION, KEY_REQUISTION_NO + " = ?",new String[]{String.valueOf(id)});
getAllUsers();
    }
}
