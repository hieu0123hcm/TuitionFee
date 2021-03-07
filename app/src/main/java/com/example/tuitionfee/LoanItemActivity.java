package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.remote.LoanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanItemActivity extends AppCompatActivity {
    LoanService loanService;
    Loan loanChoose;

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

        Bundle extras = getIntent().getExtras();
        String loandate = extras.getString("loandate");
        String studentid = extras.getString("studentid");
        String expireddate =extras.getString("expireddate");
        String amount =extras.getString("amount");
        String loanstatus =extras.getString("loanstatus");
        String amountreturned =extras.getString("amountreturned");
        String loan_id =extras.getString("loan_id");
        String bundleid =extras.getString("bundleid");

        edtloanID.setText(loan_id);
        edtstudentId.setText(studentid);
        edtLoandate.setText(loandate);
        edtExpireddate.setText(expireddate);
        edtBundleid.setText(bundleid);
        edtAmount.setText(amount);
        edtLoanstatus.setText(loanstatus);
        edtAmountreturned.setText(amountreturned);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long Id = Long.parseLong(edtloanID.getText().toString().trim());
                Date loandate = null;
                Date expiredDate = null;
                try {
                    loandate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(edtLoandate.getText().toString().trim());
                    expiredDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(edtExpireddate.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String studentId = edtstudentId.getText().toString().trim();
                Long bundleid =  Long.parseLong(edtBundleid.getText().toString().trim());
                Long amount =  Long.parseLong(edtAmount.getText().toString().trim());
                String loanstatus = edtLoanstatus.getText().toString().trim();
                Long amountreturned =  Long.parseLong(edtAmountreturned.getText().toString().trim());
                loanChoose.setId(Id);
                loanChoose.setAmount(amount);
                loanChoose.setAmountReturned(amountreturned);
                loanChoose.setBundleId(bundleid);
                loanChoose.setExpiredDate(expiredDate);
                loanChoose.setStudentId(studentId);
                loanChoose.setLoanStatus(loanstatus);
                loanChoose.setLoanDate(loandate);
                update(loanChoose);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Loan> call = loanService.deleteLoan( Long.parseLong(edtloanID.getText().toString().trim()));
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




    }
    public void update(Loan loan){
        Call<Loan> call = loanService.updateLoan(loan);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoanItemActivity.this, "loan update successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}