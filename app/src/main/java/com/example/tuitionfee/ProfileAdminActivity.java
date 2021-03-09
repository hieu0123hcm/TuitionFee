package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileAdminActivity extends AppCompatActivity {

    TextView txtAccount;
    TextView txtID;
    TextView txtName;
    TextView txtBirthday;
    TextView txtMajor;
    TextView txtSemester;
    AdminService adminService;

    Admin admin;
    Date birthDate;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin2);

        txtID = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtMajor = findViewById(R.id.txtMajor);
        txtSemester = findViewById(R.id.txtSemester);

        Bundle extras = getIntent().getExtras();
        String account_id = extras.getString("account_id");
        String role = extras.getString("role").trim();
        txtAccount = findViewById(R.id.txtAccount);
        txtAccount.setText(role);

        birthDate = new Date();

        if(adminService == null){
            adminService = APIUtils.getAdminService();
        }
        Call<Admin> call = adminService.findByAccountId(account_id);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful()){
                    admin = new Admin();
                    admin = response.body();
                    txtID.setText(admin.getAdmin_id());
                    txtName.setText(admin.getFullname());

                    try {
                        birthDate  = sdf.parse(admin.getBirthdate().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String birthday = output.format(birthDate);
                    txtBirthday.setText(birthday);
                }
            }
            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }
}