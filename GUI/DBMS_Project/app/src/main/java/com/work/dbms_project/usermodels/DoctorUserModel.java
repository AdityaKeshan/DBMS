package com.work.dbms_project.usermodels;

import java.io.Serializable;

public class DoctorUserModel implements Serializable {
    String clinic_no, firstname, lastname, speciality, telephone;

    public DoctorUserModel(String clinic_no, String firstname, String lastname, String speciality, String telephone) {
        this.clinic_no = clinic_no;
        this.firstname = firstname;
        this.lastname = lastname;
        this.speciality = speciality;
        this.telephone = telephone;
    }

    public DoctorUserModel() {
    }

    public String getClinic_no() {
        return clinic_no;
    }

    public void setClinic_no(String clinic_no) {
        this.clinic_no = clinic_no;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    }

