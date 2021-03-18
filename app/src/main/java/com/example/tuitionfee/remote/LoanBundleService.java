package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Admin;
import com.example.tuitionfee.model.LoanBundle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoanBundleService {
    @GET("loanbundle/{loanBundleID}")
    Call<LoanBundle> findByLoanBundleID(@Path("loanBundleID") Long loanBundleID);
}
