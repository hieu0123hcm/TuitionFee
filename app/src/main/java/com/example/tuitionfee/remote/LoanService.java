package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoanService {
    @GET("loan/{studentid}")
    Call<List<Loan>> getLoanFromStudentID(@Path("studentid") String StudentID );


}
