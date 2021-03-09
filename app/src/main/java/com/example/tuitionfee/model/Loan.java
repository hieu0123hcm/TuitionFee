package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Loan {
    @SerializedName("loanId")
    @Expose
    private Long loanId;

    @SerializedName("loanDate")
    private String loanDate;

    @SerializedName("studentId")
    private String studentId;

    @SerializedName("expiredDate")
    private String expiredDate;

    @SerializedName("bundleId")
    private Long bundleId;

    @SerializedName("amount")
    private Long amount;

    @SerializedName("loanStatus")
    private String loanStatus;

    @SerializedName("amountReturned")
    private Long amountReturned;

    public Loan(Long loanId, String loanDate, String studentId, String expiredDate, Long bundleId, Long amount, String loanStatus, Long amountReturned) {
        this.loanId = loanId;
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

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
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
