package com.example.tuitionfee.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String account_id;
    private String role ;

    public Account() {
    }

    public Account(String account_id, String role) {
        this.account_id = account_id;
        this.role = role;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}