package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Payment {
    @SerializedName("payment_id")
    @Expose
    private Long payment_id;

    @SerializedName("semester")
    private int semester;

    @SerializedName("studentID")
    private String studentID;

    @SerializedName("created_on")
    private Date created_on;

    @SerializedName("amount")
    private Long amount;

    @SerializedName("approval")
    private Boolean approval;

    public Boolean getApproval() {
        return approval;
    }

    public Payment() {
    }

    public Payment(Long payment_id, int semester, String studentID, Date created_on, Long amount, Boolean approval) {
        this.payment_id = payment_id;
        this.semester = semester;
        this.studentID = studentID;
        this.created_on = created_on;
        this.amount = amount;
        this.approval = approval;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
