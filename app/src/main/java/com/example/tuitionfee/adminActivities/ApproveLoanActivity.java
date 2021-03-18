package com.example.tuitionfee.adminActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuitionfee.LoginActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.adapter.UnactiveLoanAdapter;
import com.example.tuitionfee.model.Loan;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanService;
import com.example.tuitionfee.studentActivities.LoanRequestActivity;
import com.example.tuitionfee.studentActivities.StudentMenuActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveLoanActivity extends AppCompatActivity {
    LoanService loanService;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_loan);
        loanService = APIUtils.getLoanService();
        listView = findViewById(R.id.txtLoanRequestListView);
        getUnactiveLoanList();
    }

    public void getUnactiveLoanList(){
        Call<List<Loan>> call = loanService.getUnactiveList();
        call.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                if(response.isSuccessful()){
                    List<Loan> loanList = response.body();
                    if(loanList != null){
                        listView.setAdapter(new UnactiveLoanAdapter(loanList, ApproveLoanActivity.this));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ApproveLoanActivity.this, ApproveLoanItemActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putLong("loan_id", loanList.get(position).getLoanId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        Toast.makeText(ApproveLoanActivity.this,"Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ApproveLoanActivity.this,"No Request Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Loan>> call, Throwable t) {
                Toast.makeText(ApproveLoanActivity.this,"Failed to connect to API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}