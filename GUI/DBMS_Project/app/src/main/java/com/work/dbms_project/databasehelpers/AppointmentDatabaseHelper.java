package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.work.dbms_project.appointment.DeleteAppointment;
import com.work.dbms_project.usermodels.AppointmentUserModel;

import java.io.Serializable;
import java.util.ArrayList;

public class AppointmentDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_APPOINTMENT = "Appointment";
    private static final String KEY_APPOINTMENT_NO = "Appointment_no";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_PAT_ID = "patient_id";
    private static final String KEY_STAFF_ID = "staff_id";
    Context con;
    /*CREATE TABLE appointment ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_APPOINTMENT = "CREATE TABLE "
            + TABLE_APPOINTMENT + "(" + KEY_APPOINTMENT_NO
            + " TEXT PRIMARY KEY," + KEY_DATE + " TEXT,"+ KEY_TIME + " TEXT,"+ KEY_PAT_ID + " TEXT,"+ KEY_STAFF_ID + " TEXT, FOREIGN KEY("+KEY_STAFF_ID +") REFERENCES "+
            StaffDatabaseHelper.TABLE_STAFF+"("+KEY_STAFF_ID+"), FOREIGN KEY("+KEY_PAT_ID +") REFERENCES "+ PatientDatabaseHelper.TABLE_PATIENT+"("+KEY_PAT_ID+"));";

    public AppointmentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
con=context;
        Log.d("table", CREATE_TABLE_APPOINTMENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_APPOINTMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_APPOINTMENT + "'");

        onCreate(db);
    }

    public void addUser(String number, String date, String time,String patient_id,String staff_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_APPOINTMENT_NO,number);
        values.put(KEY_DATE,date);
        values.put(KEY_TIME,time);
        values.put(KEY_PAT_ID,patient_id);
        values.put(KEY_STAFF_ID,staff_id);
        Log.d("table", CREATE_TABLE_APPOINTMENT);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_APPOINTMENT, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public ArrayList<AppointmentUserModel> getAllUsers() {
        ArrayList<AppointmentUserModel> userModelArrayList = new ArrayList<AppointmentUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_APPOINTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                AppointmentUserModel userModel = new AppointmentUserModel();
                userModel.setAppointment_no(c.getString(c.getColumnIndex(KEY_APPOINTMENT_NO)));
                userModel.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                userModel.setTime(c.getString(c.getColumnIndex(KEY_TIME)));
                userModel.setPatient_id(c.getString(c.getColumnIndex(KEY_PAT_ID)));
                userModel.setStaff_id(c.getString(c.getColumnIndex(KEY_STAFF_ID)));

                Toast.makeText(con, c.getString(c.getColumnIndex(KEY_STAFF_ID)), Toast.LENGTH_SHORT).show();

                // adding to Appointment list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }
    public AppointmentUserModel getResult(String id) {
        ArrayList<AppointmentUserModel> userModelArrayList = new ArrayList<AppointmentUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_APPOINTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        AppointmentUserModel userModel=new AppointmentUserModel();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String z=c.getString(c.getColumnIndex(KEY_APPOINTMENT_NO));
                if(id.equals(z)) {
                    userModel.setAppointment_no(z);
                    userModel.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                    userModel.setTime(c.getString(c.getColumnIndex(KEY_TIME)));
                    userModel.setPatient_id(c.getString(c.getColumnIndex(KEY_PAT_ID)));
                    userModel.setStaff_id(c.getString(c.getColumnIndex(KEY_STAFF_ID)));
                    return userModel;
                }
                Toast.makeText(con, c.getString(c.getColumnIndex(KEY_STAFF_ID)), Toast.LENGTH_SHORT).show();

                // adding to Appointment list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return null;

    }
//    public void updateUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_FIRSTNAME, name);
//        db.update(TABLE_USER, values, KEY_APPOINTMENT_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.update(TABLE_USER_HOBBY, valuesHobby, KEY_APPOINTMENT_NO + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.update(TABLE_USER_CITY, valuesCity, KEY_APPOINTMENT_NO + " = ?", new String[]{String.valueOf(id)});
//    }
//
    public void deleteUSer(String id) {

        // delete row in appointment table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_APPOINTMENT, KEY_APPOINTMENT_NO + " = ?",new String[]{String.valueOf(id)});
        ArrayList<AppointmentUserModel>ab=getAllUsers();

    }

}
