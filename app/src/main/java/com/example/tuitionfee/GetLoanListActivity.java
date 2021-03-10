package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetLoanListActivity extends AppCompatActivity {
    LoanService loanService;
    List<Loan> loanList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_loan_list);

        listView = (ListView) findViewById(R.id.loanListView);
        loanService = APIUtils.getLoanService();

        getLoanList();
    }

    public void getLoanList(){
        Call<List<Loan>> call = loanService.getLoanFromStudentID("SE140002");
        call.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                if(response.isSuccessful()){
                    loanList = response.body();
                    if(loanList != null){
                        listView.setAdapter(new LoanCustomListAdapter(GetLoanListActivity.this, loanList));
                        Toast.makeText(GetLoanListActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Loan>> call, Throwable t) {
                Toast.makeText(GetLoanListActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}