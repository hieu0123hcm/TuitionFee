package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.model.Studying;
import com.example.tuitionfee.model.Subject;
import com.example.tuitionfee.remote.SubjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectItemActivity extends AppCompatActivity {
    SubjectService subjectService;
    Subject subjectChoose;

    EditText txtSubjectID ;
    EditText txtSubjectName;
    EditText txtTuititionfee;
    EditText txtDescription;
    Button btnUpdate;
    Button btnDelete;
    Button btnBack;


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
        btnBack = findViewById(R.id.btnDelete);


        Bundle extras = getIntent().getExtras();
        String subjectID = extras.getString("subjectid");
        String subjectName = extras.getString("subject");
        String tuititionfee =extras.getString("tuitionfee");
        String description =extras.getString("description");

        txtDescription.setText(description);
        txtSubjectID.setText(subjectID);
        txtSubjectName.setText(subjectName);
        txtTuititionfee.setText(tuititionfee);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectId = txtSubjectID.getText().toString().trim();
                String subjectName = txtSubjectName.getText().toString().trim();
                Float tuitionFee = Float.parseFloat(txtTuititionfee.getText().toString().trim());
                String description = txtDescription.getText().toString().trim();
                subjectChoose.setSubjectid(subjectId);
                subjectChoose.setSubject(subjectName);
                subjectChoose.setDescription(description);
                subjectChoose.setTuitionfee(tuitionFee);
                update(subjectChoose);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Subject> call = subjectService.deleteSubject(subjectID.trim());
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
    public void update(Subject subject){
        Call<Subject> call = subjectService.updateSubject(subject);
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

}