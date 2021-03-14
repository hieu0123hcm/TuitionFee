package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Admin {
    @SerializedName("admin_id")
    @Expose
    private String admin_id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("birthdate")
    private Date birthdate;

    @SerializedName("account_id")
    private String account_id;

    public Admin(String admin_id, String fullname, Date birthdate, String account_id) {
        this.admin_id = admin_id;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.account_id = account_id;
    }

    public Admin() {
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}
