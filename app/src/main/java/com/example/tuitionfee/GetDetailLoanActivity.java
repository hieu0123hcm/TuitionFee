package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.adapter.TransactionCustomListAdapter;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.LoanBundle;
import com.example.tuitionfee.model.Transaction;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanBundleService;
import com.example.tuitionfee.remote.TransactionService;
import com.example.tuitionfee.studentActivities.LoanRequestActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDetailLoanActivity extends AppCompatActivity {
    Loan loan;
    TextView studentID, loanDate, expiredDate, bundleID, amount, loanStatus, amountreturned, txtRemainingAmount;
    TransactionService transactionService;
    LoanBundleService loanBundleService;
    LoanBundle loanBundle;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_detail_loan);
        Bundle extras = getIntent().getExtras();
        loan = (Loan)extras.get("loanDTO");
        loanBundleService = APIUtils.getLoanBundleService();
        studentID = findViewById(R.id.txtStudentid1);
        studentID.setText(loan.getStudentId());

        System.out.println(loan.getExpiredDate());
        System.out.println(loan.getBundleId());
        System.out.println(loan.getAmount());
        System.out.println(loan.getLoanStatus());
        System.out.println(loan.getAmountReturned());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

        Date expiredDate1 = new Date();
        Date loanDate1 = new Date();

        System.out.println(loan.getLoanDate());

        try {
            expiredDate1  = sdf.parse(loan.getExpiredDate());
            loanDate1 = sdf.parse(loan.getLoanDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedExpired = output.format(expiredDate1);
        String formattedLoanDate = output.format(loanDate1);


        expiredDate = findViewById(R.id.txtExpireddate1);
        expiredDate.setText(formattedExpired);
        amount = findViewById(R.id.txtAmount1);

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        amount.setText(formatter.format(loan.getAmount()));
        loanStatus = findViewById(R.id.txtLoanstatus1);
        loanStatus.setText(loan.getLoanStatus());
        amountreturned = findViewById(R.id.txtAmountreturned1);
        amountreturned.setText(formatter.format(loan.getAmountReturned()));
        loanDate = findViewById(R.id.txtStartDate);
        loanDate.setText(formattedLoanDate);
        txtRemainingAmount =findViewById(R.id.txtRemainingAmount);
        txtRemainingAmount.setText(formatter.format(loan.getAmount() - loan.getAmountReturned()));
        transactionService = APIUtils.getTransactionService();

        listView = findViewById(R.id.lvTransaction);
        getTransactionList(loan.getLoanId());

        getLoanBundle(loan.getBundleId());
    }

    public void getTransactionList(Long loanId){
        Call<List<Transaction>> call = transactionService.getTransactionByLoanId(loanId);
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if(response.isSuccessful()){
                    List<Transaction> transactionList = response.body();
                    if(transactionList != null) {
                        listView.setAdapter(new TransactionCustomListAdapter(GetDetailLoanActivity.this, transactionList));
                    }else{
                        Toast.makeText(GetDetailLoanActivity.this,"No Transaction found",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Toast.makeText(GetDetailLoanActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLoanBundle(Long loanBundleId){
        Call<LoanBundle> call = loanBundleService.findByLoanBundleID(loanBundleId);
        call.enqueue(new Callback<LoanBundle>() {
            @Override
            public void onResponse(Call<LoanBundle> call, Response<LoanBundle> response) {
                if(response.isSuccessful()){
                    loanBundle = response.body();
                    TextView txtBundleRate = findViewById(R.id.txtBundleRate);
                    TextView txtBundleMonth = findViewById(R.id.txtBundleMonth);

                    txtBundleRate.setText(String.valueOf(loanBundle.getRate()));
                    txtBundleMonth.setText(String.valueOf(loanBundle.getMonth()));
                }
            }

            @Override
            public void onFailure(Call<LoanBundle> call, Throwable t) {
                Toast.makeText(GetDetailLoanActivity.this,"Error Making Connection To API",Toast.LENGTH_SHORT).show();
            }
        });
    }
}