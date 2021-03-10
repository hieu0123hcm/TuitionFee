package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Notification;
import com.example.tuitionfee.model.Studying;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NotificationService {
    @POST("notification/create")
    Call<Notification> createNotification(@Body Notification notification);

    @GET("notification/notread")
    Call<List<Notification>> getNotReadNotification();
}
