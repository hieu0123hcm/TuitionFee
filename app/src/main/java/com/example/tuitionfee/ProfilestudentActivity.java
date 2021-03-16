package com.example.tuitionfee;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.model.Admin;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.AdminService;
import com.example.tuitionfee.remote.StudentService;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilestudentActivity extends AppCompatActivity {

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

    StudentService studentSevice;
    AdminService adminService;

    Admin admin;
    Student student;
    //Date birthDate;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        txtID = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtMajor = findViewById(R.id.txtMajor);
        txtSemester = findViewById(R.id.txtSemester);

        Bundle extras = getIntent().getExtras();


        String account_id = extras.getString("key");
       // String role = extras.getString("role").trim();
        txtAccount = findViewById(R.id.txtAccount);
       // txtAccount.setText(role);

       // birthDate = new Date();

        if (studentSevice == null) {
            studentSevice = APIUtils.getStudentService();
        }
        Call<Student> call = studentSevice.getStudentByAccID(account_id);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()) {
                    student = new Student();
                    student = response.body();
                    txtID.setText(student.getStudent_id());
                    txtName.setText(student.getFullname());
//                    try {
//                        birthDate = sdf.parse(student.getBirthdate().toString());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
               //     String birthday = output.format(birthDate);
                   // txtBirthday.setText(birthday);
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