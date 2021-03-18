package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoanBundle implements Serializable {
    @SerializedName("bundleId")
    @Expose
    private Long bundleId;
    @SerializedName("rate")
    private float rate;
    @SerializedName("amount")
    private Long amount;
    @SerializedName("month")
    private int month;

    public LoanBundle(Long bundleId, float rate, Long amount, int month) {
        this.bundleId = bundleId;
        this.rate = rate;
        this.amount = amount;
        this.month = month;
    }

    public LoanBundle() {
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
