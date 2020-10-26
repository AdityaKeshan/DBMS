package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.work.dbms_project.usermodels.StaffUserModel;

import java.util.ArrayList;

public class StaffDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project_staff";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_STAFF = "Staff";
    static final String TABLE_QUALIFICATION = "Qualification";
    private static final String KEY_STAFF_ID = "Staff_id";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_TELEPHONE = "telephone";
    private static final String KEY_DOB = "dob";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_WARD_NO = "Ward_no";
    private static final String KEY_DEGREE = "Degree";
    private static final String KEY_INSTI = "Institution";

    /*CREATE TABLE staff ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_STAFF = "CREATE TABLE "
            + TABLE_STAFF + "(" + KEY_STAFF_ID
            + " TEXT PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"+KEY_LASTNAME + " TEXT,"+ KEY_ADDRESS + " TEXT,"+ KEY_TELEPHONE + " TEXT,"+KEY_DOB +  " TEXT,"
            +KEY_SALARY +  " TEXT,"+KEY_WARD_NO+  " TEXT, FOREIGN KEY("+KEY_WARD_NO +") REFERENCES "+ WardDatabaseHelper.TABLE_WARD+"("+KEY_WARD_NO+"));";
    private static  final String CREATE_TABLE_QUALIFICATION= "CREATE TABLE "
            + TABLE_QUALIFICATION + "(" + KEY_STAFF_ID
            + " TEXT PRIMARY KEY," + KEY_DEGREE + " TEXT,"+KEY_INSTI + " TEXT, FOREIGN KEY("+KEY_STAFF_ID +") REFERENCES "+ TABLE_STAFF+"("+KEY_STAFF_ID+"));";
    public StaffDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        con=context;
        Log.d("table", CREATE_TABLE_STAFF);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STAFF);
        db.execSQL(CREATE_TABLE_QUALIFICATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STAFF + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUALIFICATION + "'");
        onCreate(db);
    }

    public void addUser(String number, String firstname,String lastname, String address,String telephone,String dob,String salary,String ward_no,String degree,String insti) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_STAFF_ID,number);
        values.put(KEY_FIRSTNAME,firstname);
        values.put(KEY_LASTNAME,lastname);
        values.put(KEY_ADDRESS,address);

        values.put(KEY_TELEPHONE,telephone);
        values.put(KEY_DOB,dob);
        values.put(KEY_SALARY,salary);
        values.put(KEY_WARD_NO,ward_no);
        long id = db.insertWithOnConflict(TABLE_STAFF, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(KEY_STAFF_ID,number);
        values1.put(KEY_DEGREE,degree);
        values1.put(KEY_INSTI,insti);
        long id1 = db1.insertWithOnConflict(TABLE_QUALIFICATION, null, values1, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("The values",String.valueOf(id)+" "+String.valueOf(id));
    }

    public ArrayList<StaffUserModel> getAllUsers() {
        ArrayList<StaffUserModel> userModelArrayList = new ArrayList<StaffUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_STAFF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                StaffUserModel userModel = new StaffUserModel();
                String k=c.getString(c.getColumnIndex(KEY_STAFF_ID));
                userModel.setStaff_id(k);
                userModel.setLastname(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                userModel.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                userModel.setTelephone(c.getString(c.getColumnIndex(KEY_TELEPHONE)));
                userModel.setDob(c.getString(c.getColumnIndex(KEY_DOB)));
                userModel.setSalary(c.getString(c.getColumnIndex(KEY_SALARY)));
                userModel.setFirstname(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userModel.setWard_no(c.getString(c.getColumnIndex(KEY_WARD_NO)));
                String selectQuery1 = "SELECT  * FROM " + TABLE_QUALIFICATION;
                SQLiteDatabase db1 = this.getReadableDatabase();
                Cursor c1 = db1.rawQuery(selectQuery1, null);
                // looping through all rows and adding to list
                if (c1.moveToFirst()) {
                    do {
                        String k1=c1.getString(c1.getColumnIndex(KEY_STAFF_ID));
                        if(k.equals(k1))
                        {
                            userModel.setDegree(c1.getString(c1.getColumnIndex(KEY_DEGREE)));
                            userModel.setInsti(c1.getString(c1.getColumnIndex(KEY_INSTI)));
                            break;
                        }
                    }while(c1.moveToNext());
                }
                Toast.makeText(con,c.getString(c.getColumnIndex(KEY_SALARY)) , Toast.LENGTH_SHORT).show();
                // adding to Staff list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }
    public StaffUserModel getResult(String id) {

        String selectQuery = "SELECT  * FROM " + TABLE_STAFF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                StaffUserModel userModel = new StaffUserModel();
                String k=c.getString(c.getColumnIndex(KEY_STAFF_ID));
                if(k.equals(id)) {
                    userModel.setStaff_id(k);
                    userModel.setLastname(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                    userModel.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                    userModel.setTelephone(c.getString(c.getColumnIndex(KEY_TELEPHONE)));
                    userModel.setDob(c.getString(c.getColumnIndex(KEY_DOB)));
                    userModel.setSalary(c.getString(c.getColumnIndex(KEY_SALARY)));
                    userModel.setFirstname(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                    userModel.setWard_no(c.getString(c.getColumnIndex(KEY_WARD_NO)));
                    String selectQuery1 = "SELECT  * FROM " + TABLE_QUALIFICATION;
                    SQLiteDatabase db1 = this.getReadableDatabase();
                    Cursor c1 = db1.rawQuery(selectQuery1, null);
                    // looping through all rows and adding to list
                    if (c1.moveToFirst()) {
                        do {
                            String k1=c1.getString(c1.getColumnIndex(KEY_STAFF_ID));
                            if(k.equals(k1))
                            {
                                userModel.setDegree(c1.getString(c1.getColumnIndex(KEY_DEGREE)));
                                userModel.setInsti(c1.getString(c1.getColumnIndex(KEY_INSTI)));
                                break;
                            }
                        }while(c1.moveToNext());
                    }
                    return userModel;
                }
            } while (c.moveToNext());
        }
        return null;
    }

    //    public void uplastnameUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_LASTNAME, name);
//        db.uplastname(TABLE_USER, values, KEY_STAFF_ID + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.uplastname(TABLE_USER_HOBBY, valuesHobby, KEY_STAFF_ID + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.uplastname(TABLE_USER_CITY, valuesCity, KEY_STAFF_ID + " = ?", new String[]{String.valueOf(id)});
//    }
//
    Context con;
    public void deleteUSer(String id) {

        // delete row in staff table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_STAFF, KEY_STAFF_ID + " = ?",new String[]{String.valueOf(id)});
        db.delete(TABLE_QUALIFICATION,KEY_STAFF_ID+ " = ?",new String[]{String.valueOf(id)});
        getAllUsers();
    }
    
}
