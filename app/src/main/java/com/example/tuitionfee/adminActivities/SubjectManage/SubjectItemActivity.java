package com.example.tuitionfee.adminActivities.SubjectManage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Subject;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.SubjectService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectItemActivity extends AppCompatActivity {
    SubjectService subjectService;
    Subject subjectChoose = new Subject();

    EditText txtSubjectID;
    EditText txtSubjectName;
    EditText txtTuititionfee;
    EditText txtDescription;
    Button btnUpdate;
    Button btnDelete;
    Button btnBack;

    String subjectName = "";
    String subjectID = "";
    String tuititionfee = "";
    String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_item);
        txtSubjectID = findViewById(R.id.txtSubjectID);
        txtSubjectName = findViewById(R.id.txtSubjectName);
        txtTuititionfee = findViewById(R.id.txtTuitition);
        txtDescription = findViewById(R.id.txtDescription);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);


        Bundle extras = getIntent().getExtras();
        subjectID = extras.getString("subjectid");
        subjectName = extras.getString("subject");
        tuititionfee = extras.getString("tuitionfee");
        description = extras.getString("description");

        txtDescription.setText(description);
        txtSubjectID.setText(subjectID);
        txtSubjectName.setText(subjectName);
        txtTuititionfee.setText(tuititionfee);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageSubjectActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectChoose = new Subject();
                subjectChoose.setTuitionFee(Long.parseLong(txtTuititionfee.getText().toString().trim()));
                subjectChoose.setSubject(txtSubjectName.getText().toString().trim());
                subjectChoose.setSubjectid(txtSubjectID.getText().toString().trim());
                subjectChoose.setDescription(txtDescription.getText().toString().trim());

                if(subjectService == null){
                    subjectService = APIUtils.getSubjectService();
                }

                Call<Subject> call = subjectService.updateSubject(subjectChoose);
                call.enqueue(new Callback<Subject>() {
                    @Override
                    public void onResponse(Call<Subject> call, Response<Subject> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SubjectItemActivity.this, "Subject update successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Subject> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectID = txtSubjectID.getText().toString().trim();

                if(subjectService == null){
                    subjectService = APIUtils.getSubjectService();
                }

                Call<Subject> call = subjectService.deleteSubject(subjectID);
//              if(call == null)
//                System.out.println(call);
//                System.out.println("Delete : " + subjectID);
                call.enqueue(new Callback<Subject>() {
                    @Override
                    public void onResponse(Call<Subject> call, Response<Subject> response) {
                        Toast.makeText(SubjectItemActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Subject> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });


    }

//    public void update(Subject subject) {
//        Call<Subject> call = subjectService.updateSubject(subject);
//        call.enqueue(new Callback<Subject>() {
//            @Override
//            public void onResponse(Call<Subject> call, Response<Subject> response) {
//                if (response.isSuccessful()) {
//                    subjectChoose = response.body();
//                    String subjectId = txtSubjectID.getText().toString().trim();
//                    String subjectName = txtSubjectName.getText().toString().trim();
//                    Float tuitionFee = Float.parseFloat(txtTuititionfee.getText().toString().trim());
//                    String description = txtDescription.getText().toString().trim();
//                    System.out.println(subjectChoose.getTuitionfee());
//                    subjectChoose.setSubjectid(subjectId);
//                    subjectChoose.setSubject(subjectName);
//                    subjectChoose.setDescription(description);
//                    subjectChoose.setTuitionfee(tuitionFee);
//                    Toast.makeText(SubjectItemActivity.this, "Subject update successfully!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Subject> call, Throwable t) {
//                Log.e("ERROR: ", t.getMessage());
//            }
//        });
//    }

}