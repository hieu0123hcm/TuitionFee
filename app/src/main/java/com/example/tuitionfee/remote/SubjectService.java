package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Subject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubjectService {
    @GET("subjects/{id}")
    Call<Subject> getSubjectByID(@Path("id") String  id);
}
