package com.example.tuitionfee.studentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tuitionfee.GetLoanListActivity;
import com.example.tuitionfee.LoginActivity;
import com.example.tuitionfee.ProfilestudentActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingActivity;
import com.example.tuitionfee.adminActivities.AdminMenuActivity;
import com.example.tuitionfee.adminActivities.ShowProfile.ProfileAdminActivity;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.StudentService;
import com.google.firebase.auth.FirebaseAuth;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mLogout:{
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentMenuActivity.this, LoginActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
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

    public void goToPayment(View view) {
        Intent intent = new Intent(this, ShowPaymentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("studentID", studentID);
        System.out.println(studentID);;
        bundle.putString("key", UID);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToStuProfile(View view) {
        Intent intent = new Intent(this, ProfilestudentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("studentID", studentID);
        System.out.println(studentID);;
        bundle.putString("key", UID);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}