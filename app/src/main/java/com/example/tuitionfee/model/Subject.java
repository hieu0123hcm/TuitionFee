package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("id")
    @Expose
    private String subjectid;
    @SerializedName("tuitionFee")
    private Long tuitionFee;
    @SerializedName( "subject")
    private String subject;
    @SerializedName("description")
    private String description;

    public Subject() {
    }

    public Subject(String subjectid, Long tuitionFee, String subject, String description) {
        this.subjectid = subjectid;
        this.tuitionFee = tuitionFee;
        this.subject = subject;
        this.description = description;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public Long getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(Long tuitionFee) {
        this.tuitionFee = tuitionFee;
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
