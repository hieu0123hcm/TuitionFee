package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Studying;
import com.example.tuitionfee.model.Subject;
import com.example.tuitionfee.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransactionService {
    @GET("trans/{loanID}")
    Call<List<Transaction>> getTransactionByLoanId(@Path("loanID") Long loanID);

    @POST("trans/create")
    Call<Transaction> addTransaction(@Body Transaction transaction);
}
