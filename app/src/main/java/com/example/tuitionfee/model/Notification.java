package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notification {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("sendid")
    private String sendid;
    @SerializedName("message")
    private String message;
    @SerializedName("created_on")
    private String created_on;
    @SerializedName("adminRead")
    private boolean isAdminRead;

    public Notification() {
    }

    public Notification(Long id, String sendid, String message, String created_on, boolean isAdminRead) {
        this.id = id;
        this.sendid = sendid;
        this.message = message;
        this.created_on = created_on;
        this.isAdminRead = isAdminRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public boolean isAdminRead() {
        return isAdminRead;
    }

    public void setAdminRead(boolean adminRead) {
        isAdminRead = adminRead;
    }


}
