package com.example.tuitionfee.adminActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.R;
import com.example.tuitionfee.adapter.NotificationCustomListAdapter;
import com.example.tuitionfee.model.Notification;
import com.example.tuitionfee.model.Payment;
import com.example.tuitionfee.model.Studying;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.NotificationService;
import com.example.tuitionfee.remote.PaymentService;
import com.example.tuitionfee.remote.StudyingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNotificationActivity extends AppCompatActivity {
    NotificationService notificationService;
    PaymentService paymentService;
    List<Notification> notificationList = new ArrayList<Notification>();
    ListView listView;
    StudyingService studyingService;

    Payment payment = new Payment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification);

        studyingService = APIUtils.getStudyingService();
        paymentService = APIUtils.getPaymentService();
        listView = (ListView) findViewById(R.id.notificationListView);

        notificationService = APIUtils.getNotificationService();
        getNotificationList();

    }

    public void getNotificationList(){
        Call<List<Notification>> call = notificationService.getNotReadNotification();
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if(response.isSuccessful()){
                    notificationList = response.body();
                    if(notificationList != null){
                        listView.setAdapter(new NotificationCustomListAdapter(AdminNotificationActivity.this, notificationList));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                approveRequest(position);
                            }
                        });
                        Toast.makeText(AdminNotificationActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        TextView txtContent = findViewById(R.id.txtContent);
                        txtContent.setText("You got no Notification!");

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Toast.makeText(AdminNotificationActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeNotificationStatus(Notification notification){
        Call<Notification> call = notificationService.createNotification(notification);
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AdminNotificationActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                    Notification notification1 = response.body();
                }
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
                Toast.makeText(AdminNotificationActivity.this, "Error Changing status", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changePaymentStatus(Payment payment){
        Call<Payment> call = paymentService.updatePayment(payment);
        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if(response.isSuccessful()){
                    System.out.println("Payment update successfully");
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Toast.makeText(AdminNotificationActivity.this, "Error Changing status", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findPaymentByID(Long id){
        Call<Payment> call = paymentService.findPaymentByID(id);
        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if(response.isSuccessful()){
                    Payment payment = response.body();
                    payment.setApproval(true);
                    changePaymentStatus(payment);
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Toast.makeText(AdminNotificationActivity.this, "Cannot find Payment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getStudyingByStudyingID(Long studyingID){
        Call<Studying> call = studyingService.getStudyingByStudyingID(studyingID);
        call.enqueue(new Callback<Studying>() {
            @Override
            public void onResponse(Call<Studying> call, Response<Studying> response) {
                if(response.isSuccessful()){
                    Studying studying = response.body();
                    studying.setStudyStatus("studying");
                    changeStudyStatus(studying);
                }

            }

            @Override
            public void onFailure(Call<Studying> call, Throwable t) {

            }
        });
    }

    public void changeStudyStatus(Studying studying){
        Call<Studying> call = studyingService.updateStudying(studying);
        call.enqueue(new Callback<Studying>() {
            @Override
            public void onResponse(Call<Studying> call, Response<Studying> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<Studying> call, Throwable t) {
            }
        });
    }



    public void approveRequest(int position){
        Notification notification = notificationList.get(position);
        notification.setAdminRead(true);
        System.out.println(notification.getMessage());
        String[] tmp = notification.getMessage().split("-");

        System.out.println(Long.parseLong(tmp[1]) + "-" +Long.parseLong(tmp[0]));



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Request?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                findPaymentByID(Long.parseLong(tmp[1]));
                getStudyingByStudyingID(Long.parseLong(tmp[0]));
                System.out.println(notification.isAdminRead());
                changeNotificationStatus(notification);
                recreate();

                dialog.dismiss();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}