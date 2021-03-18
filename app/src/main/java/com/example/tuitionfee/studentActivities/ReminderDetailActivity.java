package com.example.tuitionfee.studentActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tuitionfee.ProfilestudentActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.LoanRemind;
import com.example.tuitionfee.model.Transaction;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanRemindService;
import com.example.tuitionfee.remote.LoanService;
import com.example.tuitionfee.remote.TransactionService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderDetailActivity extends AppCompatActivity {
    Long loanReminderID;
    LoanRemindService loanRemindService;
    LoanService loanService;
    LoanRemind loanRemind;
    Transaction transaction = new Transaction();
    TransactionService transactionService;
    Loan loan;
    TextView txtLoanID2, txtAmount2, txtSendDate;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);
        Bundle extras = getIntent().getExtras();
        loanReminderID = extras.getLong("loanReminderID");
        UID = extras.getString("key");
        loanRemindService = APIUtils.getLoanRemindService();
        loanService = APIUtils.getLoanService();
        transactionService = APIUtils.getTransactionService();
        txtAmount2 = findViewById(R.id.txtAmount2);
        txtLoanID2 = findViewById(R.id.txtLoanID2);
        txtSendDate = findViewById(R.id.txtSendDate);

        getLoanReminder(loanReminderID);
    }

    public void getLoanReminder(Long loanReminderID){
        Call<LoanRemind> call = loanRemindService.getLoanRemindByID(loanReminderID);
        call.enqueue(new Callback<LoanRemind>() {
            @Override
            public void onResponse(Call<LoanRemind> call, Response<LoanRemind> response) {
                if(response.isSuccessful()){
                    loanRemind = response.body();
                    getLoan(loanRemind.getLoanId());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

                    Date transDate = new Date();
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    try {
                        transDate  = sdf.parse(loanRemind.getSendDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String formattedTransDate = output.format(transDate);

                    txtSendDate.setText(formattedTransDate);
                    txtAmount2.setText(formatter.format(loanRemind.getAmount()));
                }
            }

            @Override
            public void onFailure(Call<LoanRemind> call, Throwable t) {

            }
        });
    }

    public void getLoan(Long loanID){
        Call<Loan> call = loanService.getLoanByID(loanID);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if(response.isSuccessful()){
                    loan = response.body();
                    txtLoanID2.setText(String.valueOf(loan.getLoanId()));
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {

            }
        });
    }

    public void makePayment(View view) {

        new AlertDialog.Builder(this)
                .setMessage("Do you want to create new Payment?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        loan.setAmountReturned(loan.getAmountReturned() + loanRemind.getAmount());
                        updateLoan(loan);
                        loanRemind.setPaid(true);
                        updateLoanReminder(loanRemind);
                        Date date = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        transaction.setDate(dateFormat.format(date));
                        transaction.setAmount(loanRemind.getAmount());
                        transaction.setLoanId(loan.getLoanId());
                        transaction.setStudentID(loanRemind.getStudentID());
                        addTransaction(transaction);

                        Intent intent = new Intent( ReminderDetailActivity.this, StudentMenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", UID);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void updateLoan(Loan loan){
        Call<Loan> call = loanService.addLoan(loan);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if(response.isSuccessful()){
                    System.out.println("Update Loan Successful");
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {

            }
        });
    }

    public void updateLoanReminder(LoanRemind loanRemind){
        Call<LoanRemind> call = loanRemindService.addLoanRemind(loanRemind);
        call.enqueue(new Callback<LoanRemind>() {
            @Override
            public void onResponse(Call<LoanRemind> call, Response<LoanRemind> response) {
                if(response.isSuccessful()){
                    System.out.println("Update LoanReminder Successful");
                }
            }

            @Override
            public void onFailure(Call<LoanRemind> call, Throwable t) {

            }
        });
    }

    public void addTransaction(Transaction transaction){
        Call<Transaction> call = transactionService.addTransaction(transaction);
        call.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if(response.isSuccessful()){
                    System.out.println("Create Transaction Successful");
                }
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {

            }
        });
    }
}