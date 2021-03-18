package com.example.tuitionfee.adminActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.LoanBundle;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanBundleService;
import com.example.tuitionfee.remote.LoanService;
import com.example.tuitionfee.studentActivities.LoanRequestActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveLoanItemActivity extends AppCompatActivity {
    LoanService loanService;
    LoanBundleService loanBundleService;
    Loan loan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_loan_item);
        loanService = APIUtils.getLoanService();
        loanBundleService = APIUtils.getLoanBundleService();
        Bundle extras = getIntent().getExtras();
        Long loan_id = extras.getLong("loan_id");
        getLoanByID(loan_id);
    }

    public void getLoanByID(long loanID){
        Call<Loan> call = loanService.getLoanByID(loanID);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if(response.isSuccessful()){
                    if(response != null){
                        loan = response.body();
                        getLoanBundle(loan.getBundleId());
                        TextView txtLoanIDApprove = findViewById(R.id.txtLoanIDApprove);
                        TextView txtStudentIDApprove = findViewById(R.id.txtStudentIDApprove);
                        TextView txtLoanAmountApprove = findViewById(R.id.txtLoanAmountApprove);
                        TextView txtExpiredDateEstApprove = findViewById(R.id.txtExpiredDateEstApprove);
                        TextView txtLoanDateEstApprove = findViewById(R.id.txtLoanDateEstApprove);
                        txtLoanIDApprove.setText(String.valueOf(loan.getLoanId()));
                        txtStudentIDApprove.setText(loan.getStudentId());
                        DecimalFormat formatter = new DecimalFormat("###,###,###");
                        txtLoanAmountApprove.setText(formatter.format(loan.getAmount()));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

                        Date expiredDate = new Date();
                        Date loanDate = new Date();

                        try {
                            expiredDate  = sdf.parse(loan.getExpiredDate());
                            loanDate = sdf.parse(loan.getLoanDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String formattedExpired = output.format(expiredDate);
                        String formattedLoanDate = output.format(loanDate);

                        txtLoanDateEstApprove.setText(formattedLoanDate);
                        txtExpiredDateEstApprove.setText(formattedExpired);

                    }
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {
                Toast.makeText(ApproveLoanItemActivity.this,"Error Making Connection To API",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLoanBundle(Long loanBundleId){
        Call<LoanBundle> call = loanBundleService.findByLoanBundleID(loanBundleId);
        call.enqueue(new Callback<LoanBundle>() {
            @Override
            public void onResponse(Call<LoanBundle> call, Response<LoanBundle> response) {
                if(response.isSuccessful()){
                    LoanBundle loanBundle = response.body();
                    TextView txtMonthlyRateApprove = findViewById(R.id.txtMonthlyRateApprove);
                    txtMonthlyRateApprove.setText(String.valueOf(loanBundle.getRate()));

                    TextView txtLoanMonthApprove = findViewById(R.id.txtLoanMonthApprove);
                    txtLoanMonthApprove.setText(String.valueOf(loanBundle.getMonth()));
                }
            }

            @Override
            public void onFailure(Call<LoanBundle> call, Throwable t) {
                Toast.makeText(ApproveLoanItemActivity.this,"Error Making Connection To API",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickToApprove(View view) {

        new AlertDialog.Builder(this)
                .setMessage("Do you want to Approve this Loan Request?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        loan.setLoanStatus("active");
                        approveLoan(loan);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void approveLoan(Loan loan){
        Call<Loan> call = loanService.addLoan(loan);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ApproveLoanItemActivity.this,"Approved", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ApproveLoanItemActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {
                Toast.makeText(ApproveLoanItemActivity.this,"Cannot Approved", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void clickToDecline(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to Decline this Loan Request?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        loan.setLoanStatus("decline");
                        approveLoan(loan);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}