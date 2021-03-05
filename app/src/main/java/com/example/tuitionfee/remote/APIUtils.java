package com.example.tuitionfee.remote;

public class APIUtils {

    private APIUtils() {
    }

    public static final String API_URL = "http://127.0.0.1:5000/";

    public static StudyingService getStudyingService() {
        return RetrofitClient.getClient(API_URL).create(StudyingService.class);
    }

    public static SubjectService getSubjectService() {
        return RetrofitClient.getClient(API_URL).create(SubjectService.class);
    }

}

