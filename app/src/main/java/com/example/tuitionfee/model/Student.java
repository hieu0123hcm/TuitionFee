package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Student {

    @SerializedName("student_id")
    @Expose
    private String id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("major")
    private String major;

    @SerializedName("semester")
    private String semester;

    @SerializedName("birthdate")
    private Date birthdate;

    @SerializedName("account_id")
    private Long account_id;

    public Student(String id, String fullname, String major, String semester, Date birthdate, Long account_id) {
        this.id = id;
        this.fullname = fullname;
        this.major = major;
        this.semester = semester;
        this.birthdate = birthdate;
        this.account_id = account_id;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }
}
