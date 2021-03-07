package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Student;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentSevice {

    @GET("student/{id}")
    Call<Student> findByAccountId(@Path("id") Long id);
}
