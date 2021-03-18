package com.example.tuitionfee.adminActivities.LoanManage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.LoanBundle;
import com.example.tuitionfee.model.LoanRemind;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanBundleService;
import com.example.tuitionfee.remote.LoanRemindService;
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

public class LoanItemActivity extends AppCompatActivity {
    LoanService loanService;
    Loan loanChoose;
    LoanRemindService loanRemindService;
    EditText edtloanID;
    EditText edtstudentId;
    EditText edtLoandate;
    EditText edtExpireddate;
    EditText edtBundleid;
    EditText edtAmount;
    EditText edtLoanstatus;
    EditText edtAmountreturned;
    Button btnUpdate;
    Button btnDelete;
    Button btnBack;
    LoanRemind loanRemind = new LoanRemind();
    LoanBundleService loanBundleService;
    Date expiredDate;
    Date loanDate;
    LoanBundle loanBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_item);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnBack = (Button) findViewById(R.id.btnBack);

        edtloanID = (EditText) findViewById(R.id.txtLoan_id);
        edtstudentId = (EditText) findViewById(R.id.txtStudentid);
        edtLoandate = (EditText) findViewById(R.id.txtLoandate);
        edtExpireddate = (EditText) findViewById(R.id.txtExpireddate);
        edtBundleid = (EditText) findViewById(R.id.txtBundleid);
        edtAmount = (EditText) findViewById(R.id.txtAmount);
        edtLoanstatus = (EditText) findViewById(R.id.txtLoanstatus);
        edtAmountreturned = (EditText) findViewById(R.id.txtAmountreturned);
        loanBundleService = APIUtils.getLoanBundleService();
        loanRemindService = APIUtils.getLoanRemindService();
        Bundle extras = getIntent().getExtras();
        String loandate = extras.getString("loandate");
        String studentid = extras.getString("studentId");

        String expireddate = extras.getString("expiredDate");

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String format = formatter.format(expireddate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

        expiredDate = new Date();
        loanDate = new Date();

        try {
            expiredDate = sdf.parse(expireddate);
            loanDate = sdf.parse(loandate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedExpired = output.format(expiredDate);
        String formattedLoanDate = output.format(loanDate);


        String amount = extras.getString("amount");
        String loanstatus = extras.getString("loanStatus");
        String amountreturned = extras.getString("amountReturned");
        String loan_id = extras.getString("loanId");
        String bundleid = extras.getString("bundleId");

        edtloanID.setText(loan_id);
        edtstudentId.setText(studentid);
        edtLoandate.setText(formattedLoanDate);
        edtExpireddate.setText(formattedExpired);
        edtBundleid.setText(bundleid);
        edtAmount.setText(amount);
        edtLoanstatus.setText(loanstatus);
        edtAmountreturned.setText(amountreturned);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id = Long.parseLong(edtloanID.getText().toString().trim());
//                Date loandate  = new Date();
//                Date expiredDate = new Date();
//                try {
//                    loandate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(edtLoandate.getText().toString().trim());
//                    expiredDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(edtExpireddate.getText().toString().trim());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                String loanDate = edtLoandate.getText().toString().trim();
                String expiredDate = edtExpireddate.getText().toString().trim();

                String studentId = edtstudentId.getText().toString().trim();
                Long bundleid = Long.parseLong(edtBundleid.getText().toString().trim());
                Long amount = Long.parseLong(edtAmount.getText().toString().trim());
                String loanstatus = edtLoanstatus.getText().toString().trim();
                Long amountreturned = Long.parseLong(edtAmountreturned.getText().toString().trim());
                loanChoose = new Loan();
                loanChoose.setLoanId(id);
                loanChoose.setAmount(amount);
                loanChoose.setAmountReturned(amountreturned);
                loanChoose.setBundleId(bundleid);
                loanChoose.setExpiredDate(expiredDate);
                loanChoose.setStudentId(studentId);
                loanChoose.setLoanStatus(loanstatus);
                loanChoose.setLoanDate(loandate);
//                update(loanChoose);

                if (loanService == null) {
                    loanService = APIUtils.getLoanService();
                }
                Call<Loan> call = loanService.updateLoan(loanChoose);
                call.enqueue(new Callback<Loan>() {
                    @Override
                    public void onResponse(Call<Loan> call, Response<Loan> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(LoanItemActivity.this, "Loan update successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Loan> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loanService == null) {
                    loanService = APIUtils.getLoanService();
                }

                Call<Loan> call = loanService.deleteLoan(Long.parseLong(edtloanID.getText().toString().trim()));
                call.enqueue(new Callback<Loan>() {
                    @Override
                    public void onResponse(Call<Loan> call, Response<Loan> response) {
                        Toast.makeText(LoanItemActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Loan> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageLoanActivity.class);
                startActivity(intent);
            }
        });
        getLoanBundle(Long.parseLong(bundleid));
        System.out.println(Long.parseLong(loan_id));
        loanRemind.setLoanId(Long.parseLong(loan_id));
        loanRemind.setStudentID(studentid);
    }

    public void clickToCreateReminder(View view) {

        createLoanRemind(loanRemind);
    }

    public void getLoanBundle(Long loanBundleId){
        Call<LoanBundle> call = loanBundleService.findByLoanBundleID(loanBundleId);
        call.enqueue(new Callback<LoanBundle>() {
            @Override
            public void onResponse(Call<LoanBundle> call, Response<LoanBundle> response) {
                if(response.isSuccessful()){
                    loanBundle = response.body();
                    if(loanBundle != null){
                        loanRemind.setAmount(loanBundle.getAmount()/loanBundle.getMonth());
                        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                        Date sendDate = new Date();
                        loanRemind.setSendDate(output.format(sendDate));
                        loanRemind.setPaid(false);
                    }

                }
            }

            @Override
            public void onFailure(Call<LoanBundle> call, Throwable t) {
                Toast.makeText(LoanItemActivity.this,"Error Making Connection To API",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createLoanRemind(LoanRemind loanRemind){
        Call<LoanRemind> call = loanRemindService.addLoanRemind(loanRemind);
        call.enqueue(new Callback<LoanRemind>() {
            @Override
            public void onResponse(Call<LoanRemind> call, Response<LoanRemind> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoanItemActivity.this,"Create Successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoanRemind> call, Throwable t) {
                Toast.makeText(LoanItemActivity.this,"Create Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}