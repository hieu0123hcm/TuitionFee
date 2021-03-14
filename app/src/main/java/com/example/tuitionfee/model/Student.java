package com.example.tuitionfee.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String student_id,major,semester,fullname ;
    private String birthday;
    private String account_id;

    public Student(String student_id, String major, String semester, String fullname, String birthday, String account_id) {
        this.student_id = student_id;
        this.major = major;
        this.semester = semester;
        this.fullname = fullname;
        this.birthday = birthday;
        this.account_id = account_id;
    }

    public Student(String major, String semester, String fullname, String birthday) {
        this.major = major;
        this.semester = semester;
        this.fullname = fullname;
        this.birthday = birthday;
    }

    public Student() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}
