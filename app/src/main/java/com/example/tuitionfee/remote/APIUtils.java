package com.example.tuitionfee.remote;

import android.widget.RelativeLayout;

import com.example.tuitionfee.model.Notification;

public class APIUtils {

        private APIUtils(){
        };

        public static final String API_URL = "https://tuitionfee.herokuapp.com/";

        public static StudyingService getStudyingService(){
            return RetrofitClient.getClient(API_URL).create(StudyingService.class);
        }

        public static SubjectService getSubjectService(){
            return RetrofitClient.getClient(API_URL).create(SubjectService.class);
        }

        public static NotificationService getNotificationService(){
            return RetrofitClient.getClient(API_URL).create(NotificationService.class);
        }

        public static PaymentService getPaymentService(){
            return RetrofitClient.getClient(API_URL).create(PaymentService.class);
        }

        public static LoanService getLoanService(){
            return RetrofitClient.getClient(API_URL).create(LoanService.class);
        }
    }

