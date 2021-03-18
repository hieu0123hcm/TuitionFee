package com.example.tuitionfee.adminActivities.LoanManage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.R;
import com.example.tuitionfee.adapter.LoanCustomListAdapter;
import com.example.tuitionfee.adapter.SubjectCustomListAdapter;
import com.example.tuitionfee.adminActivities.SubjectManage.ManageSubjectActivity;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageLoanActivity extends AppCompatActivity {

    LoanService loanService;
    Button btnAdd;
    List<Loan> listLoan = new ArrayList<>();
    ListView listView;
    Button btnSearch;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_loan);

        listView = (ListView) findViewById(R.id.lstLoan);
        listView.setAdapter(null);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtSearch = (EditText) findViewById(R.id.edtSearchLoan);

        //Get List
        if(loanService == null){
            loanService = APIUtils.getLoanService();
        }
        Call<List<Loan>> call = loanService.getList();

        call.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                if (response.isSuccessful()) {
                    listLoan = response.body();
                    if(listLoan != null){
                        System.out.println(listLoan.get(0));
                        listView.setAdapter(new LoanCustomListAdapter(ManageLoanActivity.this, listLoan));
                        Toast.makeText(ManageLoanActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ManageLoanActivity.this, "This list is empty", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Loan>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddLoanActivity.class);
                startActivity(intent);
            }
        });

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoan();
            }
        });
    }

    private void getLoan() {
      //  listLoan.isEmpty();
        String id = edtSearch.getText().toString().trim();
        if (loanService == null){
            loanService = APIUtils.getLoanService();
        }

        Call<List<Loan>> call = loanService.getLoanFromStudentID(id);
        call.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                listLoan = response.body();
                if(response.body() != null){
                    System.out.println(listLoan);
                    listView.setAdapter(new LoanCustomListAdapter(ManageLoanActivity.this, listLoan));
                    Toast.makeText(ManageLoanActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ManageLoanActivity.this, "ID Not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Loan>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }


}