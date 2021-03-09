package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuitionfee.model.Studying;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.StudyingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudyingActivity extends AppCompatActivity {
    StudyingService studyingService;
    List<Studying> studyingList = new ArrayList<Studying>();
    ListView listView;
    Button btnManageSubject;
    Button btnManageLoan;
    Button btnProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getRole, accountID
//        Bundle extras = getIntent().getExtras();
//        String account_id = extras.getString("account_id");
//        String role = extras.getString("role").trim();

       String account_id = "12";
        String role = "admin";

        listView = (ListView) findViewById(R.id.studyingListView);
        studyingService = APIUtils.getStudyingService();

        //getStudyingList();


        /*Test ManageSubject*/
        btnManageSubject = (Button) findViewById(R.id.btnManageSubject);
        btnManageSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageSubjectActivity.class);
                startActivity(intent);
            }
        });

        btnManageLoan = (Button) findViewById(R.id.btnManageLoan);
        btnManageLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageLoanActivity.class);
                startActivity(intent);
            }
        });



        /*Test Profile*/
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(role.equals("student")) {
                    Intent intent = new Intent(getApplicationContext(), ProfilestudentActivity.class);
                    intent.putExtra("role",role);
                    intent.putExtra("account_id",account_id);
                    startActivity(intent);
                }else if(role.equals("admin")){
                    Intent intent = new Intent(getApplicationContext(), ProfileAdminActivity.class);
                    intent.putExtra("role",role);
                    intent.putExtra("account_id",account_id);
                    startActivity(intent);
                }

            }
        });

    }

    public void getStudyingList(){
        Call<List<Studying>> call = studyingService.getStudyingByStuIDAndStatus("SE140002", "failed");
        call.enqueue(new Callback<List<Studying>>() {
            @Override
            public void onResponse(Call<List<Studying>> call, Response<List<Studying>> response) {
                if(response.isSuccessful()){
                    studyingList = response.body();
                    listView.setAdapter(new StudyingCustomListAdapter(StudyingActivity.this, studyingList));
                    Toast.makeText(StudyingActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Studying>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}