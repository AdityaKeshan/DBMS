package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.work.dbms_project.usermodels.DoctorUserModel;
import com.work.dbms_project.usermodels.DrugUserModel;

import java.util.ArrayList;

public class DrugDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project_drug";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_DRUG = "Drug";
    private static final String KEY_DRUG_ID = "Drug_id";
    private static final String KEY_DRUG_NAME = "drug_name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_STOCK = "stock";
    private static final String KEY_PRICE = "price";

    /*CREATE TABLE drug ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/
    Context con;
    private static final String CREATE_TABLE_DRUG = "CREATE TABLE "
            + TABLE_DRUG + "(" + KEY_DRUG_ID
            + " TEXT PRIMARY KEY," + KEY_DRUG_NAME + " TEXT,"+ KEY_TYPE + " TEXT,"+ KEY_STOCK + " TEXT,"+KEY_PRICE +  " TEXT);";

    public DrugDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        con=context;
        Log.d("table", CREATE_TABLE_DRUG);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DRUG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DRUG + "'");
        onCreate(db);
    }

    public void addUser(String number, String drug_name, String type,String stock,String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_DRUG_ID,number);
        values.put(KEY_DRUG_NAME,drug_name);
        values.put(KEY_TYPE,type);
        values.put(KEY_STOCK,stock);
        values.put(KEY_PRICE,price);

        long id = db.insertWithOnConflict(TABLE_DRUG, null, values, SQLiteDatabase.CONFLICT_IGNORE);


        Log.d("The values",String.valueOf(id)+" "+String.valueOf(id));
    }

    public ArrayList<DrugUserModel> getAllUsers() {
        ArrayList<DrugUserModel> userModelArrayList = new ArrayList<DrugUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_DRUG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DrugUserModel userModel = new DrugUserModel();
                String k=c.getString(c.getColumnIndex(KEY_DRUG_ID));
                userModel.setDrug_id(k);
                userModel.setDrug_name(c.getString(c.getColumnIndex(KEY_DRUG_NAME)));
                userModel.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                userModel.setStock(c.getString(c.getColumnIndex(KEY_STOCK)));
                userModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                Toast.makeText(con, c.getString(c.getColumnIndex(KEY_DRUG_NAME)), Toast.LENGTH_SHORT).show();

                // adding to Drug list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }
    public DrugUserModel getResult(String id) {
        ArrayList<DrugUserModel> userModelArrayList = new ArrayList<DrugUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_DRUG;
        SQLiteDatabase db = this.getReadableDatabase();
        DrugUserModel userModel=new DrugUserModel();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String z=c.getString(c.getColumnIndex(KEY_DRUG_ID));
                if(id.equals(z)) {
                    userModel.setDrug_id(z);
                    userModel.setDrug_name(c.getString(c.getColumnIndex(KEY_DRUG_NAME)));
                    userModel.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                    userModel.setStock(c.getString(c.getColumnIndex(KEY_STOCK)));
                    userModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                    return userModel;
                }
                // adding to Drug list

            } while (c.moveToNext());
        }
        return null;

    }

    //    public void updrug_nameUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_DRUG_NAME, name);
//        db.updrug_name(TABLE_USER, values, KEY_DRUG_ID + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.updrug_name(TABLE_USER_HOBBY, valuesHobby, KEY_DRUG_ID + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.updrug_name(TABLE_USER_CITY, valuesCity, KEY_DRUG_ID + " = ?", new String[]{String.valueOf(id)});
//    }
//
    public void deleteUSer(String id) {

        // delete row in drug table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_DRUG, KEY_DRUG_ID + " = ?",new String[]{String.valueOf(id)});
        getAllUsers();

    }
}
