package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("subjectid")
    @Expose
    private String subjectid;

    @SerializedName("tuitionFee")
    private Float tuitionfee;

    @SerializedName("subject")
    private String subject;

    @SerializedName("description")
    private String description;

    public Subject() {
    }

    public Subject(String subjectid, Float tuitionfee, String subject, String description) {
        this.subjectid = subjectid;
        this.tuitionfee = tuitionfee;
        this.subject = subject;
        this.description = description;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public Float getTuitionfee() {
        return tuitionfee;
    }

    public void setTuitionfee(Float tuitionfee) {
        this.tuitionfee = tuitionfee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
