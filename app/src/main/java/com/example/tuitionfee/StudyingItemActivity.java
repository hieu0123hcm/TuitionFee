package com.example.tuitionfee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.model.Notification;
import com.example.tuitionfee.model.Payment;
import com.example.tuitionfee.model.Studying;
import com.example.tuitionfee.model.Subject;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.NotificationService;
import com.example.tuitionfee.remote.PaymentService;
import com.example.tuitionfee.remote.StudyingService;
import com.example.tuitionfee.remote.SubjectService;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudyingItemActivity extends AppCompatActivity {
    StudyingService studyingService;
    SubjectService subjectService;
    NotificationService notificationService;
    PaymentService paymentService;
    Studying studying = new Studying();
    Subject subject = new Subject();
    Payment payment = new Payment();
    private Long paymentID = Long.getLong("0");
    String account_id;
    Notification notification = new Notification();
    String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying_item);

        studyingService = APIUtils.getStudyingService();
        subjectService = APIUtils.getSubjectService();
        notificationService = APIUtils.getNotificationService();
        paymentService = APIUtils.getPaymentService();

        Bundle extras = getIntent().getExtras();
        String studyingID = extras.getString("studying_id");
        studentID = extras.getString("studentID");
        account_id = extras.getString("account_id");
        System.out.println("StudyingItemAc" + extras.getString("account_id"));
        getStudyingByStudyingID(Long.parseLong(studyingID));
    }

    public void getStudyingByStudyingID(Long studyingID){
        Call<Studying> call = studyingService.getStudyingByStudyingID(studyingID);
        call.enqueue(new Callback<Studying>() {
            @Override
            public void onResponse(Call<Studying> call, Response<Studying> response) {
                if(response.isSuccessful()){

                    TextView txtSubjectID = findViewById(R.id.txtSubjectID);
                    TextView txtSubject = findViewById(R.id.txtSubject);
                    TextView txtSemesterNo = findViewById(R.id.txtSemesterNo);

                    studying = response.body();

                    txtSubjectID.setText(studying.getSubjectID());
                    txtSubject.setText(studying.getSubject());
                    txtSemesterNo.setText(String.valueOf(studying.getSemesterNo()));
                    getSubjectByID(studying.getSubjectID());
                    Toast.makeText(StudyingItemActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Studying> call, Throwable t) {

                Toast.makeText(StudyingItemActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSubjectByID(String subjectID){
        Call<Subject> call = subjectService.getSubjectByID(subjectID);
        call.enqueue(new Callback<Subject>() {
            @Override
            public void onResponse(Call<Subject> call, Response<Subject> response) {
                subject = response.body();
                TextView txtTuitionFee = findViewById(R.id.txtTuitionFee);
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                txtTuitionFee.setText(formatter.format(subject.getTuitionFee()));
                Toast.makeText(StudyingItemActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Subject> call, Throwable t) {
                Toast.makeText(StudyingItemActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createNotification(Notification notification){
        Call<Notification> call = notificationService.createNotification(notification);
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if(response.isSuccessful()){
                    Toast.makeText(StudyingItemActivity.this, "Request created successfully!", Toast.LENGTH_SHORT).show();
                    Button request = findViewById(R.id.btnRequest);
                    request.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
                Toast.makeText(StudyingItemActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(StudyingItemActivity.this, "Error Changing status", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createPayment(Payment payment){
        Call<Payment> call = paymentService.updatePayment(payment);
        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if(response.isSuccessful()){
                    payment.setPayment_id(response.body().getPayment_id());
                    notification.setMessage(String.valueOf(studying.getId()) + "-" + String.valueOf(payment.getPayment_id()));
                    createNotification(notification);
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Toast.makeText(StudyingItemActivity.this, "Error Creating Payment", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createRequestToAdmin(View view) {


        notification.setSendid(account_id);
        System.out.println("last"  +account_id);
        notification.setAdminRead(false);
        System.out.println(notification.getSendid());
        java.util.Date date=new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        System.out.println(date);
        notification.setCreated_on(format);



        payment.setAmount(subject.getTuitionFee());
        payment.setApproval(false);
        payment.setSemester(studying.getSemesterNo());
        payment.setStudentID(studying.getStudentID());
        payment.setCreated_on(format);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Request?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                createPayment(payment);
                studying.setStudyStatus("pending");
                changeStudyStatus(studying);
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

    public void getBackToStudyingActivity(View view) {
        Intent intent = new Intent(this, StudyingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("studentID", studentID);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}