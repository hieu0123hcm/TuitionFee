package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Loan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoanService {

    @GET("loan/{id}")
    Call<Loan> getLoanByStudentId(@Path("id") String id);

    @GET("loans")
    Call<List<Loan>> getList();

    @POST("loan/add")
    Call<Loan> addLoan(@Body Loan loan);

    @PUT("loan/update")
    Call<Loan> updateLoan(@Body Loan loan);

    @DELETE("loan/delete/{id}")
    Call<Loan> deleteLoan(@Path("id") Long id);
}
