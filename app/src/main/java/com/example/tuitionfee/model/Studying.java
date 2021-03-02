package com.example.tuitionfee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Studying {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("subjectID")
    private String subjectID;
    @SerializedName("studentid")
    private String studentID;
    @SerializedName("studystatus")
    private String studyStatus;
    @SerializedName("subject")
    private String subject;
    @SerializedName("semesterNo")
    private int semesterNo;

    public Studying(int id, String subjectID, String studentID, String studyStatus, String subject, int semesterNo) {
        this.id = id;
        this.subjectID = subjectID;
        this.studentID = studentID;
        this.studyStatus = studyStatus;
        this.subject = subject;
        this.semesterNo = semesterNo;
    }

    public Studying() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }
}
