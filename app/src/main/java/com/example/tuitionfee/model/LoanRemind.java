package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanRemind {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("amount")
    private Long amount;
    @SerializedName( "loanId")
    private Long loanId;
    @SerializedName("sendDate")
    private String sendDate;
    @SerializedName("expiredDate")
    private String expiredDate;
    @SerializedName("studentId")
    private String studentID;
    @SerializedName("paid")
    private Boolean isPaid;

    public LoanRemind() {
    }

    public LoanRemind(Long id, Long amount, Long loanId, String sendDate, String expiredDate, String studentID, Boolean isPaid) {
        this.id = id;
        this.amount = amount;
        this.loanId = loanId;
        this.sendDate = sendDate;
        this.expiredDate = expiredDate;
        this.studentID = studentID;
        this.isPaid = isPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
