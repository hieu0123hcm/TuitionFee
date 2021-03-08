package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Student;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentSevice {

    @GET("student/{account_id}")
    Call<Student> findByAccountId(@Path("account_id") Long account_id);
}
