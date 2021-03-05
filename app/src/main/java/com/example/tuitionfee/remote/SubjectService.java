package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SubjectService {
    @GET("subject/{id}")
    Call<Subject> getSubjectByID(@Path("subjectid") String  subjectid);

    @GET("subjects")
    Call<List<Subject>> getList();

    @POST("subject/add")
    Call<Subject> addSubject(@Body Subject subject);

    @PUT("subject/update")
    Call<Subject> updateSubject(@Body Subject subject);

    @DELETE("subject/delete/{id}")
    Call<Subject> deleteSubject(@Path("id") String id);
}
