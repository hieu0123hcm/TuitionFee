package com.example.tuitionfee.remote;


import com.example.tuitionfee.model.Notification;
import com.example.tuitionfee.model.Payment;
import com.example.tuitionfee.model.Studying;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaymentService {
    @POST("payment/create")
    Call<Payment> updatePayment(@Body Payment payment);

    @GET("payment/{id}")
    Call<Payment> findPaymentByID(@Path("id") Long id);

    @GET("payment/approve/{id}")
    Call<List<Payment>> findPaymentByStuID(@Path("id") String id);
}
