package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Admin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AdminService {

    @GET("admin/{id}")
    Call<Admin> findByAccountId(@Path("id") Long id);

}
