package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLoanActivity extends AppCompatActivity {

    LoanService loanService;
    Loan loanAdd;
    Button btnCancel;
    Button btnAdd;
    EditText edtloanID;
    EditText edtstudentId;
    EditText edtLoandate;
    EditText edtExpireddate;
    EditText edtBundleid;
    EditText edtAmount;
    EditText edtLoanstatus;
    EditText edtAmountreturned;

    Date loandate;
    Date expiredDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan2);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        edtloanID = (EditText) findViewById(R.id.edtloanID);
        edtstudentId = (EditText) findViewById(R.id.edtstudentId);
        edtLoandate = (EditText) findViewById(R.id.edtLoandate);
        edtExpireddate = (EditText) findViewById(R.id.edtExpireddate);
        edtBundleid = (EditText) findViewById(R.id.edtBundleid);
        edtAmount = (EditText) findViewById(R.id.edtAmount);
        edtLoanstatus = (EditText) findViewById(R.id.edtLoanstatus);
        edtAmountreturned = (EditText) findViewById(R.id.edtAmountreturned);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageLoanActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long id = Long.parseLong(edtloanID.getText().toString().trim());

//                try {
//                    loandate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(edtLoandate.getText().toString().trim());
//                     expiredDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(edtExpireddate.getText().toString().trim());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                String loanDate = edtLoandate.getText().toString().trim();
                String  expiredDate = edtExpireddate.getText().toString().trim();

                String studentId = edtstudentId.getText().toString().trim();
                Long bundleid =  Long.parseLong(edtBundleid.getText().toString().trim());
                Long amount =  Long.parseLong(edtAmount.getText().toString().trim());
                String loanstatus = edtLoanstatus.getText().toString().trim();
                Long amountreturned =  Long.parseLong(edtAmountreturned.getText().toString().trim());

                loanAdd= new Loan();
                loanAdd.setLoanId(id);
                System.out.println("id"+id);
                loanAdd.setAmount(amount);
                System.out.println("amount"+amount);
                loanAdd.setAmountReturned(amountreturned);
                System.out.println("amountreturned"+amountreturned);
                loanAdd.setBundleId(bundleid);
                System.out.println("bundleid"+bundleid);
                loanAdd.setExpiredDate(expiredDate);
                System.out.println("expiredDate"+expiredDate);
                loanAdd.setStudentId(studentId);
                System.out.println("studentId"+studentId);
                loanAdd.setLoanStatus(loanstatus);
                System.out.println("loanstatus"+loanstatus);
                loanAdd.setLoanDate(loanDate);
                System.out.println("loandate"+loandate);
                addLoan(loanAdd);

            }
        });
        
        
    }

    public void addLoan(Loan loan) {
        if (loanService == null){
            loanService = APIUtils.getLoanService();
        }

        Call<Loan> call = loanService.addLoan(loan);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if (response.isSuccessful()) {
                    System.out.println("hihihihihihihihihihihihihihihihihiad loan");
                    Toast.makeText(AddLoanActivity.this, "Loan created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}