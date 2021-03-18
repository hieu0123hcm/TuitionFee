package com.example.tuitionfee.studentActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingActivity;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.model.LoanBundle;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanBundleService;
import com.example.tuitionfee.remote.LoanService;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanRequestActivity extends AppCompatActivity {
    Spinner spinner ;
    LoanBundleService loanBundleService;
    LoanBundle loanBundle;
    TextView txtRate;
    TextView txtTotalAmount;
    LoanService loanService;
    Loan loan = new Loan();
    String studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request);
        Bundle extras = getIntent().getExtras();
        studentID = extras.getString("studentID");
        spinner = (Spinner) findViewById(R.id.loanSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.loan_bundle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getLoanBundle(Long.valueOf(position + 1));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loanBundleService = APIUtils.getLoanBundleService();
        loanService = APIUtils.getLoanService();
        System.out.println(txtRate);

    }

    public void getLoanBundle(Long loanBundleId){
        Call<LoanBundle> call = loanBundleService.findByLoanBundleID(loanBundleId);
        call.enqueue(new Callback<LoanBundle>() {
            @Override
            public void onResponse(Call<LoanBundle> call, Response<LoanBundle> response) {
                if(response.isSuccessful()){
                    loanBundle = response.body();
                     txtRate = findViewById(R.id.txtMonthlyRate);
                     txtRate.setText(String.valueOf(loanBundle.getRate()));
                     txtTotalAmount = findViewById(R.id.txtLoanAmount);

                    TextView txtMonth = findViewById(R.id.txtLoanMonth);
                    txtMonth.setText(String.valueOf(loanBundle.getMonth()));

                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    txtTotalAmount.setText(formatter.format(loanBundle.getAmount()) + " VND");
                    Long monthlyAmount = loanBundle.getAmount()/ loanBundle.getMonth();
                    TextView txtMonthlyAmount = findViewById(R.id.txtMonthlyAmount);
                    txtMonthlyAmount.setText(formatter.format(monthlyAmount) + " VND");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 7);
                    String loanDate = (String)(dateFormat.format(c.getTime()));

                    TextView txtLoanDate = findViewById(R.id.txtLoanDateEst);
                    txtLoanDate.setText(loanDate);

                    loan.setLoanDate(loanDate);

                    c.add(Calendar.MONTH, loanBundle.getMonth());
                    String expiredDate = (String)(dateFormat.format(c.getTime()));

                    TextView txtExpiredDate = findViewById(R.id.txtExpiredDateEst);
                    txtExpiredDate.setText(expiredDate);

                    loan.setExpiredDate(expiredDate);

                    loan.setStudentId(studentID);
                    loan.setAmount(loanBundle.getAmount());
                    loan.setAmountReturned(Long.valueOf(0));
                    loan.setBundleId(loanBundle.getBundleId());
                    loan.setLoanStatus("unactive");
                }
            }

            @Override
            public void onFailure(Call<LoanBundle> call, Throwable t) {
                Toast.makeText(LoanRequestActivity.this,"Error Making Connection To API",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void test(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to create new Loan Request?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        createLoan(loan);
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }

    public void createLoan(Loan loan){
        Call<Loan> call = loanService.addLoan(loan);
        call.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoanRequestActivity.this,"Create Loan Request Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoanRequestActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {
                Toast.makeText(LoanRequestActivity.this,"Create Loan Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}