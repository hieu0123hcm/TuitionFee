package com.example.tuitionfee.remote;

public class APIUtils {

        private APIUtils(){
        };

        public static final String API_URL = "https://tuitionfee.herokuapp.com/";

        public static StudyingService getStudyingService(){
            return RetrofitClient.getClient(API_URL).create(StudyingService.class);
        }

    }

