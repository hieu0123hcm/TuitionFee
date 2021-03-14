package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuitionfee.adapter.LoanCustomListAdapterForStudent;
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
    String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_loan_list);

        listView = (ListView) findViewById(R.id.loanListView);
        loanService = APIUtils.getLoanService();
        Bundle extras = getIntent().getExtras();
        studentID = extras.getString("studentID");
        System.out.println("Loan list co student id la " + studentID);
        getLoanList(studentID);
    }

    public void getLoanList(String studentID){
        Call<List<Loan>> call = loanService.getLoanFromStudentID(studentID);
        call.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                if(response.isSuccessful()){
                    loanList = response.body();
                    if(loanList != null){
                        listView.setAdapter(new LoanCustomListAdapterForStudent(GetLoanListActivity.this, loanList));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Loan loan = loanList.get(position);
                                Intent intent = new Intent(GetLoanListActivity.this, GetDetailLoanActivity.class);
                                intent.putExtra("loanDTO", loan);
                                startActivity(intent);
                            }
                        });
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