package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Loan {
    @SerializedName("loan_id")
    @Expose
    private Long id;

    @SerializedName("loandate")
    private Date loanDate;

    @SerializedName("studentid")
    private String studentId;

    @SerializedName("expireddate")
    private Date expiredDate;

    @SerializedName("bundleid")
    private Long bundleId;

    @SerializedName("amount")
    private Long amount;

    @SerializedName("loanstatus")
    private String loanStatus;

    @SerializedName("amountreturned")
    private Long amountReturned;

    public Loan(Long id, Date loanDate, String studentId, Date expiredDate, Long bundleId, Long amount, String loanStatus, Long amountReturned) {
        this.id = id;
        this.loanDate = loanDate;
        this.studentId = studentId;
        this.expiredDate = expiredDate;
        this.bundleId = bundleId;
        this.amount = amount;
        this.loanStatus = loanStatus;
        this.amountReturned = amountReturned;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Long getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Long amountReturned) {
        this.amountReturned = amountReturned;
    }
}
