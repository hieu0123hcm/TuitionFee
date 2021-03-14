package com.example.tuitionfee.studentActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tuitionfee.GetLoanListActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingActivity;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.StudentService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMenuActivity extends AppCompatActivity {
    private String studentID;
    private String UID;
    StudentService studentService;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        studentService = APIUtils.getStudentService();

        Bundle extras = getIntent().getExtras();

        UID = extras.getString("key");
        getStudentIDByAccID(UID);
    }

    private void getStudentIDByAccID(String accID){
        Call<Student> call = studentService.getStudentByAccID(accID);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    studentID = response.body().getStudent_id();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
    }

    public void goToViewReStudyCourse(View view) {
        Intent intent = new Intent(this, StudyingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("studentID", studentID);
        System.out.println(studentID);;
        bundle.putString("key", UID);
        System.out.println("StudentMenuActivity UID" + UID);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToGetLoanList(View view) {
        Intent intent = new Intent(this, GetLoanListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("studentID", studentID);
        System.out.println(studentID);;
        bundle.putString("key", UID);
        System.out.println("StudentMenuActivity UID" + UID);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}