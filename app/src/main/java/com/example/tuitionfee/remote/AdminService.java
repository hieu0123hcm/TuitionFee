package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Admin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AdminService {

    @GET("admin/{account_id}")
    Call<Admin> findByAccountId(@Path("account_id") Long account_id);

}
