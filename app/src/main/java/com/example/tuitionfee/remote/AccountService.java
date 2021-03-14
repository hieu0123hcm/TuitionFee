package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountService {

    @GET("account/{account_id}")
    Call<Account> getAccount(@Path("account_id")String id);

}
