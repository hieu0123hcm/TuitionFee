package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.LoanRemind;
import com.example.tuitionfee.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoanRemindService {
    @GET("loanremind/{studentID}")
    Call<List<LoanRemind>> getLoanRemindByStuID(@Path("studentID") String studentID);

    @GET("loanremind/id/{loanRemindID}")
    Call<LoanRemind> getLoanRemindByID(@Path("loanRemindID") Long loanRemindID);

    @POST("loanremind/add")
    Call<LoanRemind> addLoanRemind(@Body LoanRemind loanRemind);
}
