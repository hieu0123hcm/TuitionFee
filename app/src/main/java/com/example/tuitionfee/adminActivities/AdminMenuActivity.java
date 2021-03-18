package com.example.tuitionfee.adminActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tuitionfee.LoginActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.adminActivities.LoanManage.ManageLoanActivity;
import com.example.tuitionfee.adminActivities.ShowProfile.ProfileAdminActivity;
import com.example.tuitionfee.adminActivities.StudentManage.StudentManagementActivity;
import com.example.tuitionfee.adminActivities.SubjectManage.ManageSubjectActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AdminMenuActivity extends AppCompatActivity {
    private String account_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Bundle extras = getIntent().getExtras();
        account_id = extras.getString("key");


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
                startActivity(new Intent(AdminMenuActivity.this, LoginActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToViewRequests(View view) {
        Intent intent = new Intent(this, AdminNotificationActivity.class);
        startActivity(intent);
    }

    public void goToManageStudent(View view) {
        Intent intent2 = new Intent(this, StudentManagementActivity.class);
        startActivity(intent2);
    }

    public void goToManageLoan(View view) {
        Intent intent2 = new Intent(this, ManageLoanActivity.class);
        startActivity(intent2);
    }

    public void goToManageSubject(View view) {
        Intent intent2 = new Intent(this, ManageSubjectActivity.class);
        startActivity(intent2);
    }

    public void showProfile(View view) {

        Intent intent = new Intent(this, ProfileAdminActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("key", account_id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void approveLoanRequest(View view) {
        Intent intent2 = new Intent(this, ApproveLoanActivity.class);
        startActivity(intent2);
    }
}