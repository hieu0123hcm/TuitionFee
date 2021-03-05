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
}
