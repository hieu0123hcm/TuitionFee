package com.example.tuitionfee.studentActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingActivity;
import com.example.tuitionfee.adapter.PaymentListAdapter;
import com.example.tuitionfee.model.Payment;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.PaymentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPaymentActivity extends AppCompatActivity {
    PaymentService paymentService;
    String studentID;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_payment);

        Bundle extras = getIntent().getExtras();
        studentID = extras.getString("studentID");

        paymentService = APIUtils.getPaymentService();
        listView = findViewById(R.id.paymentListView);
        getPaymentList(studentID);
    }

    public void getPaymentList(String studentID){
        Call<List<Payment>> call = paymentService.findPaymentByStuID(studentID);
        call.enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                if(response.isSuccessful()){
                    List<Payment> payments = response.body();
                    if(response.body() != null){
                        listView.setAdapter(new PaymentListAdapter(payments, ShowPaymentActivity.this));
                        Toast.makeText(ShowPaymentActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ShowPaymentActivity.this,"No Payment found",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Toast.makeText(ShowPaymentActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}