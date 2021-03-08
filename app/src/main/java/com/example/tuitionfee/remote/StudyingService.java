package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Studying;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudyingService {
    @GET("studying/{studentID}/{studyStatus}")
    Call<List<Studying>> getStudyingByStuIDAndStatus(@Path("studentID") String  studentID, @Path("studyStatus") String studyStatus);

}
