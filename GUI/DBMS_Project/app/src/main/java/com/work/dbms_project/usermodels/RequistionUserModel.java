package com.work.dbms_project.usermodels;

import java.io.Serializable;

public class RequistionUserModel implements Serializable {
    String requistion_no, quantity, date_of_order, date_received, ward_no,staff_id,drug_id;

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public RequistionUserModel() {
    }

    public String getRequistion_no() {
        return requistion_no;
    }

    public void setRequistion_no(String requistion_no) {
        this.requistion_no = requistion_no;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(String date_of_order) {
        this.date_of_order = date_of_order;
    }

    public String getDate_received() {
        return date_received;
    }

    public void setDate_received(String date_received) {
        this.date_received = date_received;
    }

    public String getWard_no() {
        return ward_no;
    }

    public void setWard_no(String ward_no) {
        this.ward_no = ward_no;
    }

}
