package com.work.dbms_project.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.work.dbms_project.usermodels.DoctorUserModel;
import com.work.dbms_project.usermodels.PatientUserModel;

import java.util.ArrayList;

public class PatientDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbms_project_pat";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_PATIENT = "Patient";
    private static final String TABLE_INPATIENT = "INPatient";
    private static final String TABLE_OUTPATIENT = "OUTPatient";
    private static final String TABLE_BED = "BED";
    private static final String KEY_PATIENT_ID = "Patient_id";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_DOB = "dob";
    private static final String KEY_CLINIC_NO = "clinic_no";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    Context con;
    private static final String KEY_IN_DATE = "indate";
    private static final String KEY_LEAVE_DATE = "LeaveDate";
    private static final String KEY_BED_NO = "BedNo";
    private static final String KEY_WARD_NO = "WARD_NO";

    private static final String KEY_OUT_DATE = "OUT_DATE";
    private static final String KEY_OUT_TIME = "OUT_time";

    /*CREATE TABLE patient ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_PATIENT = "CREATE TABLE " + TABLE_PATIENT + " ( " + KEY_PATIENT_ID
            + " TEXT PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"+KEY_LASTNAME + " TEXT,"+ KEY_CLINIC_NO + " TEXT,"+KEY_ADDRESS +  " TEXT,"
            +KEY_PHONE +  " TEXT,"+ KEY_DOB + " TEXT, FOREIGN KEY("+KEY_CLINIC_NO +") REFERENCES "+ DoctorDatabaseHelper.TABLE_DOCTOR+"("+KEY_CLINIC_NO+"));";;

    private static final String CREATE_TABLE_INPATIENT = "CREATE TABLE "  + TABLE_INPATIENT + " ( " + KEY_PATIENT_ID
            + " TEXT PRIMARY KEY," + KEY_IN_DATE + " TEXT,"+KEY_LEAVE_DATE + " TEXT,"+ KEY_BED_NO + " TEXT,"+ KEY_WARD_NO + " TEXT, FOREIGN KEY("+KEY_PATIENT_ID+") REFERENCES "+ TABLE_PATIENT+"("+KEY_PATIENT_ID+")," +
            "FOREIGN KEY("+KEY_BED_NO +") REFERENCES "+ TABLE_BED+"("+KEY_BED_NO+"), FOREIGN KEY("+KEY_WARD_NO +") REFERENCES "+ WardDatabaseHelper.TABLE_WARD+"("+KEY_WARD_NO+"));";

    private static final String CREATE_TABLE_OUTPATIENT= "CREATE TABLE "
            + TABLE_OUTPATIENT + " (" + KEY_PATIENT_ID
            + " TEXT PRIMARY KEY," + KEY_OUT_DATE + " TEXT,"+ KEY_OUT_TIME + " TEXT, FOREIGN KEY("+KEY_PATIENT_ID+") REFERENCES "+ TABLE_PATIENT+"("+KEY_PATIENT_ID+"));";


    private static final String CREATE_TABLE_BED="CREATE TABLE "
            + TABLE_BED + " (" + KEY_BED_NO
            + " TEXT PRIMARY KEY," + KEY_WARD_NO + "  TEXT, FOREIGN KEY("+KEY_WARD_NO+") REFERENCES "+ WardDatabaseHelper.TABLE_WARD+"("+KEY_WARD_NO+"));";

    public PatientDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        con=context;
        Log.d("table", CREATE_TABLE_PATIENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PATIENT);
        db.execSQL(CREATE_TABLE_INPATIENT);
        db.execSQL(CREATE_TABLE_OUTPATIENT);
        db.execSQL(CREATE_TABLE_BED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_PATIENT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_INPATIENT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_OUTPATIENT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BED + "'");
        onCreate(db);
    }

    public void addUser(String number, String firstname,String lastname,String clinic_no,String address,String phone, String dob
                        ,String indate,String leavedate,String bedno,String wardno,String outdate,String outtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        if(!firstname.equals(" ")) {
            ContentValues values = new ContentValues();
            values.put(KEY_PATIENT_ID, number);
            values.put(KEY_FIRSTNAME, firstname);
            values.put(KEY_LASTNAME, lastname);
            values.put(KEY_DOB, dob);

            values.put(KEY_CLINIC_NO, clinic_no);
            values.put(KEY_ADDRESS, address);
            values.put(KEY_PHONE, phone);


            long id = db.insertWithOnConflict(TABLE_PATIENT, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }
            ContentValues values1 = new ContentValues();
        values1.put(KEY_PATIENT_ID,number);
            values1.put(KEY_IN_DATE, indate);
            values1.put(KEY_LEAVE_DATE, leavedate);
            values1.put(KEY_WARD_NO, wardno);
            values1.put(KEY_BED_NO, bedno);
            long id1 = db.insertWithOnConflict(TABLE_INPATIENT, null, values1, SQLiteDatabase.CONFLICT_IGNORE);
            ContentValues values2 = new ContentValues();
        values2.put(KEY_PATIENT_ID,number);
            values2.put(KEY_OUT_DATE, outdate);
            values2.put(KEY_OUT_TIME, outtime);
            long id2 = db.insertWithOnConflict(TABLE_OUTPATIENT, null, values2, SQLiteDatabase.CONFLICT_IGNORE);
            ContentValues values3 = new ContentValues();
            values3.put(KEY_WARD_NO, wardno);
            values3.put(KEY_BED_NO, bedno);
            long id3 = db.insertWithOnConflict(TABLE_BED, null, values3, SQLiteDatabase.CONFLICT_IGNORE);
            Log.d("The values", String.valueOf(id3) + " " + String.valueOf(id3));
    }
    public PatientUserModel getResult(String id) {
        ArrayList<PatientUserModel> userModelArrayList = new ArrayList<PatientUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT;
        SQLiteDatabase db = this.getReadableDatabase();
        PatientUserModel userModel=new PatientUserModel();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String z=c.getString(c.getColumnIndex(KEY_PATIENT_ID));
                if(id.equals(z)) {
                    userModel.setPatient_id(z);
                    userModel.setFirstname(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                    userModel.setLastname(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                    userModel.setClinic_no(c.getString(c.getColumnIndex(KEY_CLINIC_NO)));
                    userModel.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                    userModel.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                    userModel.setDob(c.getString(c.getColumnIndex(KEY_DOB)));
                    String selectQuery1 = "SELECT  * FROM " + TABLE_INPATIENT;
                    SQLiteDatabase db1 = this.getReadableDatabase();
                    Cursor c1 = db1.rawQuery(selectQuery1, null);
                    userModel.setIn_date(" ");
                    userModel.setLeave_date(" ");
                    userModel.setBed_no(" ");
                    userModel.setWard_no(" ");
                    userModel.setOut_time(" ");
                    userModel.setOut_date(" ");
                    // looping through all rows and adding to list
                    if (c1.moveToFirst()) {
                        do {
                            String k = c1.getString(c1.getColumnIndex(KEY_PATIENT_ID));
                            if (id.equals(k)) {
                                userModel.setWard_no(c1.getString(c1.getColumnIndex(KEY_WARD_NO)));
                                userModel.setBed_no(c1.getString(c1.getColumnIndex(KEY_BED_NO)));
                                userModel.setIn_date(c1.getString(c1.getColumnIndex(KEY_IN_DATE)));
                                userModel.setLeave_date(c1.getString(c1.getColumnIndex(KEY_LEAVE_DATE)));
                                break;
                            }
                        } while (c1.moveToNext());

                    }
                    String selectQuery2 = "SELECT  * FROM " + TABLE_OUTPATIENT;
                    SQLiteDatabase db2 = this.getReadableDatabase();
                    Cursor c2 = db2.rawQuery(selectQuery2, null);
                    if(c2.moveToFirst()) {
                        do {
                            String z1=c2.getString(c2.getColumnIndex(KEY_PATIENT_ID));
                            if(z1!=null && id!=null) {
                                if (z1.equals(id)) {
                                    userModel.setOut_date(c2.getString(c2.getColumnIndex(KEY_OUT_DATE)));
                                    userModel.setOut_time(c2.getString(c2.getColumnIndex(KEY_OUT_TIME)));
                                    return userModel;
                                }
                            }
                        }while (c2.moveToNext());

                    }
                    return userModel;
                }
                // adding to Patient list

            } while (c.moveToNext());
        }
        return null;

    }
    public ArrayList<PatientUserModel> getAllUsers() {
        ArrayList<PatientUserModel> userModelArrayList = new ArrayList<PatientUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PatientUserModel userModel = new PatientUserModel();
                String k=c.getString(c.getColumnIndex(KEY_PATIENT_ID));
                userModel.setPatient_id(k);
                userModel.setLastname(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                userModel.setDob(c.getString(c.getColumnIndex(KEY_DOB)));
                userModel.setClinic_no(c.getString(c.getColumnIndex(KEY_CLINIC_NO)));
                userModel.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                userModel.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                userModel.setFirstname(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                String selectQuery1 = "SELECT  * FROM " + TABLE_INPATIENT;
                SQLiteDatabase db1 = this.getReadableDatabase();
                Cursor c1 = db1.rawQuery(selectQuery1, null);
                if(c1.moveToFirst()) {
                    do {
                        String z = c1.getString(c1.getColumnIndex(KEY_PATIENT_ID));
                        if (z != null && k != null) {
                            if (z.equals(k)) {
                                userModel.setIn_date(c1.getString(c1.getColumnIndex(KEY_IN_DATE)));
                                userModel.setLeave_date(c1.getString(c1.getColumnIndex(KEY_LEAVE_DATE)));
                                userModel.setWard_no(c1.getString(c1.getColumnIndex(KEY_WARD_NO)));
                                userModel.setBed_no(c1.getString(c1.getColumnIndex(KEY_BED_NO)));
                                break;
                            }
                        }
                    }while (c1.moveToNext()) ;

                }
                String selectQuery2 = "SELECT  * FROM " + TABLE_OUTPATIENT;
                SQLiteDatabase db2 = this.getReadableDatabase();
                Cursor c2 = db2.rawQuery(selectQuery2, null);
                if(c2.moveToFirst()) {
                    do {
                        String z=c2.getString(c2.getColumnIndex(KEY_PATIENT_ID));
                        if(z!=null && k!=null) {
                            if (z.equals(k)) {
                                userModel.setOut_date(c2.getString(c2.getColumnIndex(KEY_OUT_DATE)));
                                userModel.setOut_time(c2.getString(c2.getColumnIndex(KEY_OUT_TIME)));
                                break;
                            }
                        }
                    }while (c2.moveToNext());
                }

                Toast.makeText(con,c.getString(c.getColumnIndex(KEY_PHONE)) , Toast.LENGTH_SHORT).show();
                // adding to Patient list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    //    public void uplastnameUser(int id, String name, String hobby, String city) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // updating name in users table
//        ContentValues values = new ContentValues();
//        values.put(KEY_LASTNAME, name);
//        db.uplastname(TABLE_USER, values, KEY_PATIENT_ID + " = ?", new String[]{String.valueOf(id)});
//
//        // updating hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.uplastname(TABLE_USER_HOBBY, valuesHobby, KEY_PATIENT_ID + " = ?", new String[]{String.valueOf(id)});
//
//        // updating city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_CITY, city);
//        db.uplastname(TABLE_USER_CITY, valuesCity, KEY_PATIENT_ID + " = ?", new String[]{String.valueOf(id)});
//    }
//
    public void deleteUSer(String id) {

        // delete row in patient table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_PATIENT, KEY_PATIENT_ID + " = ?",new String[]{String.valueOf(id)});

        //deleting from users_hobby table
        db.delete(TABLE_INPATIENT, KEY_PATIENT_ID + " = ?", new String[]{String.valueOf(id)});

        //deleting from users_city table
        db.delete(TABLE_OUTPATIENT, KEY_PATIENT_ID + " = ?",new String[]{String.valueOf(id)});
        getAllUsers();
    }
}
