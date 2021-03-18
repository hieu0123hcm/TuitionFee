package com.example.tuitionfee.studentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tuitionfee.GetLoanListActivity;
import com.example.tuitionfee.LoginActivity;
import com.example.tuitionfee.ProfilestudentActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingActivity;
import com.example.tuitionfee.adapter.LoanRemindAdapter;
import com.example.tuitionfee.adminActivities.AdminMenuActivity;
import com.example.tuitionfee.adminActivities.ShowProfile.ProfileAdminActivity;
import com.example.tuitionfee.model.LoanRemind;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.LoanRemindService;
import com.example.tuitionfee.remote.StudentService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMenuActivity extends AppCompatActivity {
    private String studentID;
    private String UID;
    StudentService studentService;
    Student student;
    LoanRemindService loanRemindService;
    ListView listView;
    TextView txtNewReminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        txtNewReminder = findViewById(R.id.txtNewReminder);
        studentService = APIUtils.getStudentService();
        loanRemindService = APIUtils.getLoanRemindService();
        Bundle extras = getIntent().getExtras();
        listView = findViewById(R.id.listViewReminder);
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

                    getReminderList(studentID);
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
    public void goToCreateLoan(View view) {
        Intent intent = new Intent(this, LoanRequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("studentID", studentID);
        System.out.println(studentID);;
        bundle.putString("key", UID);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getReminderList(String studentID){
        Call<List<LoanRemind>> call = loanRemindService.getLoanRemindByStuID(studentID);
        call.enqueue(new Callback<List<LoanRemind>>() {
            @Override
            public void onResponse(Call<List<LoanRemind>> call, Response<List<LoanRemind>> response) {
                if(response.isSuccessful()){
                    List<LoanRemind> loanRemindList = response.body();
                    if(loanRemindList != null){
                        txtNewReminder.setText("You got " + loanRemindList.size() +" Reminder(s)");
                        listView.setAdapter(new LoanRemindAdapter(loanRemindList, StudentMenuActivity.this));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(StudentMenuActivity.this, ReminderDetailActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putLong("loanReminderID", loanRemindList.get(position).getId());
                                bundle.putString("key", UID);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LoanRemind>> call, Throwable t) {

            }
        });
    }


}
