package com.work.dbms_project.usermodels;

import java.io.Serializable;

public class DrugUserModel implements Serializable {
    String drug_id, drug_name, type, stock, price;

    public DrugUserModel(String drug_id, String drug_name, String type, String stock, String price) {
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.type = type;
        this.stock = stock;
        this.price = price;
    }

    public DrugUserModel() {
    }

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}
