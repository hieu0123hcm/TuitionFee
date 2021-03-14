package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tuitionfee.adapter.TransactionCustomListAdapter;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.Transaction;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.TransactionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDetailLoanActivity extends AppCompatActivity {
    Loan loan;
    TextView studentID, loanDate, expiredDate, bundleID, amount, loanStatus, amountreturned;
    TransactionService transactionService;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_detail_loan);
        Bundle extras = getIntent().getExtras();
        loan = (Loan)extras.get("loanDTO");

        studentID = findViewById(R.id.txtStudentid1);
        studentID.setText(loan.getStudentId());

        System.out.println(loan.getExpiredDate());
        System.out.println(loan.getBundleId());
        System.out.println(loan.getAmount());
        System.out.println(loan.getLoanStatus());
        System.out.println(loan.getAmountReturned());

        expiredDate = findViewById(R.id.txtExpireddate1);
        expiredDate.setText(loan.getExpiredDate());
        bundleID = findViewById(R.id.txtBundleid1);
        bundleID.setText(String.valueOf(loan.getBundleId()));
        amount = findViewById(R.id.txtAmount1);
        amount.setText(String.valueOf(loan.getAmount()));
        loanStatus = findViewById(R.id.txtLoanstatus1);
        loanStatus.setText(loan.getLoanStatus());
        amountreturned = findViewById(R.id.txtAmountreturned1);
        amountreturned.setText(String.valueOf(loan.getAmountReturned()));

        transactionService = APIUtils.getTransactionService();

        listView = findViewById(R.id.lvTransaction);
        getTransactionList(loan.getLoanId());
    }

    public void getTransactionList(Long loanId){
        Call<List<Transaction>> call = transactionService.getTransactionByLoanId(loanId);
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if(response.isSuccessful()){
                    List<Transaction> transactionList = response.body();
                    listView.setAdapter(new TransactionCustomListAdapter(GetDetailLoanActivity.this, transactionList));
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });
    }
}