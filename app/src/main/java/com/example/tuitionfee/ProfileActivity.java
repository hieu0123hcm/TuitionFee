package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuitionfee.model.Admin;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.AdminService;
import com.example.tuitionfee.remote.StudentSevice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView txtAccount;
    TextView txtID;
    TextView txtName;
    TextView txtBirthday;
    TextView txtMajor;
    TextView labelMajor;
    View viewMajor;
    TextView labelSemester;
    TextView txtSemester;
    View viewSemester;

    ImageView imgSemester;
    ImageView imgMajor;

    StudentSevice studentSevice;
    AdminService adminService;

    Admin admin;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtID = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtMajor = findViewById(R.id.txtMajor);
        labelMajor = findViewById(R.id.labelMajor);
        viewMajor = findViewById(R.id.viewMajor);
        labelSemester = findViewById(R.id.labelSemester);
        txtSemester = findViewById(R.id.txtSemester);
        viewSemester = findViewById(R.id.viewSemester);

        Bundle extras = getIntent().getExtras();


        Long account_id = extras.getLong("account_id");
        String role = extras.getString("role").trim();
        txtAccount = findViewById(R.id.txtAccount);
        txtAccount.setText(role);

        if (role.equals("admin")){
//            txtMajor.setVisibility(View.INVISIBLE);
//            labelMajor.setVisibility(View.INVISIBLE);
//            viewMajor.setVisibility(View.INVISIBLE);
//            labelSemester.setVisibility(View.INVISIBLE);
//            txtSemester.setVisibility(View.INVISIBLE);
//            viewSemester.setVisibility(View.INVISIBLE);
//            imgSemester.setVisibility(View.INVISIBLE);
//            imgMajor.setVisibility(View.INVISIBLE);

            if(adminService == null){
                adminService = APIUtils.getAdminService();
            }
            Call<Admin> call = adminService.findByAccountId(account_id);
            call.enqueue(new Callback<Admin>() {
                @Override
                public void onResponse(Call<Admin> call, Response<Admin> response) {
                    if (response.isSuccessful()){
                        admin = response.body();
                        txtID.setText(admin.getAdmin_id().toString());
                        txtName.setText(admin.getFullname());
                        txtBirthday.setText(admin.getBirthdate().toString());
                        txtAccount.setText(admin.getAccount_id());
                    }
                }
                @Override
                public void onFailure(Call<Admin> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });


        }else if(role.equals("student")){
            if(studentSevice == null){
                studentSevice = APIUtils.getStudentService();
            }
            Call<Student> call = studentSevice.findByAccountId(account_id);
            call.enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    if (response.isSuccessful()){
                        student = response.body();
                        txtID.setText(student.getId());
                        txtName.setText(student.getFullname());
                        txtBirthday.setText(student.getBirthdate().toString());
                        txtMajor.setText(student.getMajor());
                        txtSemester.setText(student.getSemester());
                    }
                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }





    }
}