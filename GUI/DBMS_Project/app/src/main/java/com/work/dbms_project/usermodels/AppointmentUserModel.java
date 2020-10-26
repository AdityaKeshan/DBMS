package com.work.dbms_project.usermodels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class AppointmentUserModel implements Serializable {
    String appointment_no,date,time,patient_id,staff_id;

    public AppointmentUserModel(String appointment_no, String date, String time, String patient_id, String staff_id) {
        this.appointment_no = appointment_no;
        this.date = date;
        this.time = time;
        this.patient_id = patient_id;
        this.staff_id = staff_id;
    }

    public AppointmentUserModel() {
    }

    public String getAppointment_no() {
        return appointment_no;
    }

    public void setAppointment_no(String appointment_no) {
        this.appointment_no = appointment_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
}
