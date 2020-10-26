package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.work.dbms_project.usermodels.DoctorUserModel;
import com.work.dbms_project.usermodels.DoctorUserModel;

import java.util.ArrayList;

public class DoctorDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project_doctor";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_DOCTOR = "Doctor";
    private static final String TABLE_DOCTOR_TELEPHONE = "Doctor_TELEPHONE";
    private static final String KEY_CLINIC_NO = "Clinic_no";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    Context con;
    private static final String KEY_SPECIALITY = "speciality";
    private static final String KEY_TELEPHONE = "telephone";

    /*CREATE TABLE doctor ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_DOCTOR = "CREATE TABLE "
            + TABLE_DOCTOR + "(" + KEY_CLINIC_NO
            + " TEXT PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"+ KEY_LASTNAME + " TEXT,"+ KEY_SPECIALITY + " TEXT);";
    private static final String CREATE_TABLE_DOCTOR_TELEPHONE = "CREATE TABLE "
            + TABLE_DOCTOR_TELEPHONE + "(" + KEY_CLINIC_NO
            + " TEXT PRIMARY KEY," + KEY_TELEPHONE +  " TEXT, FOREIGN KEY("+KEY_CLINIC_NO +") REFERENCES "+ TABLE_DOCTOR+"("+KEY_CLINIC_NO+"));";

    public DoctorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        con=context;
        Log.d("table", CREATE_TABLE_DOCTOR);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DOCTOR);
        db.execSQL(CREATE_TABLE_DOCTOR_TELEPHONE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DOCTOR + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DOCTOR_TELEPHONE + "'");
        onCreate(db);
    }

    public void addUser(String number, String firstname, String lastname,String speciality,String telephone) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_CLINIC_NO,number);
        values.put(KEY_FIRSTNAME,firstname);
        values.put(KEY_LASTNAME,lastname);
        values.put(KEY_SPECIALITY,speciality);

        Log.d("table", CREATE_TABLE_DOCTOR);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_DOCTOR, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        ContentValues values1 = new ContentValues();
        values1.put(KEY_CLINIC_NO,number);
        values1.put(KEY_TELEPHONE,telephone);
        long id1 = db.insertWithOnConflict(TABLE_DOCTOR_TELEPHONE, null, values1, SQLiteDatabase.CONFLICT_IGNORE);
    }
    public DoctorUserModel getResult(String id) {
        ArrayList<DoctorUserModel> userModelArrayList = new ArrayList<DoctorUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_DOCTOR;
        SQLiteDatabase db = this.getReadableDatabase();
        DoctorUserModel userModel=new DoctorUserModel();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String z=c.getString(c.getColumnIndex(KEY_CLINIC_NO));
                boolean f=id.equalsIgnoreCase(z.trim());
                if(f) {
                    userModel.setClinic_no(z);
                    userModel.setFirstname(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                    userModel.setLastname(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                    userModel.setSpeciality(c.getString(c.getColumnIndex(KEY_SPECIALITY)));
                    String selectQuery1 = "SELECT  * FROM " + TABLE_DOCTOR_TELEPHONE;
                    SQLiteDatabase db1 = this.getReadableDatabase();
                    Cursor c1 = db1.rawQuery(selectQuery1, null);
                    userModel.setTelephone(" ");
                    // looping through all rows and adding to list
                    if (c1.moveToFirst()) {
                        do {
                            String k = c1.getString(c1.getColumnIndex(KEY_CLINIC_NO));
                            if (id.equals(k)) {
                                userModel.setTelephone(c1.getString(c1.getColumnIndex(KEY_TELEPHONE)));
                                return userModel;
                            }
                        } while (c1.moveToNext());

                    }
                    return userModel;
                }

                // adding to Doctor list

            } while (c.moveToNext());
        }
        return null;

    }
    public ArrayList<DoctorUserModel> getAllUsers() {
        ArrayList<DoctorUserModel> userModelArrayList = new ArrayList<DoctorUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_DOCTOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DoctorUserModel userModel = new DoctorUserModel();
                String k=c.getString(c.getColumnIndex(KEY_CLINIC_NO));
                userModel.setClinic_no(k);
                userModel.setFirstname(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userModel.setLastname(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                userModel.setSpeciality(c.getString(c.getColumnIndex(KEY_SPECIALITY)));
                String selectQuery1 = "SELECT  * FROM " + TABLE_DOCTOR_TELEPHONE;
                SQLiteDatabase db1 = this.getReadableDatabase();
                Toast.makeText(con, c.getString(c.getColumnIndex(KEY_FIRSTNAME)), Toast.LENGTH_SHORT).show();
                Cursor c1 = db1.rawQuery(selectQuery1, null);
                if(c1.moveToFirst())
                {
                    do {
                        if(k.equals(c1.getString(c1.getColumnIndex(KEY_CLINIC_NO)))) {
                            userModel.setTelephone(c1.getString(c1.getColumnIndex(KEY_TELEPHONE)));
                        }
                    }while(c1.moveToNext());
                }

                // adding to Doctor list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    //    public void upfirstnameUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_FIRSTNAME, name);
//        db.upfirstname(TABLE_USER, values, KEY_CLINIC_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.upfirstname(TABLE_USER_HOBBY, valuesHobby, KEY_CLINIC_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.upfirstname(TABLE_USER_CITY, valuesCity, KEY_CLINIC_NO + " = ?", new String[]{String.valueOf(id)});
//    }
//
    public void deleteUSer(String id) {

        // delete row in doctor table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_DOCTOR, KEY_CLINIC_NO + " = ?",new String[]{String.valueOf(id)});

        //deleting from users_hobby table
        db.delete(TABLE_DOCTOR_TELEPHONE, KEY_CLINIC_NO + " = ?", new String[]{String.valueOf(id)});

        getAllUsers();
    }

}
