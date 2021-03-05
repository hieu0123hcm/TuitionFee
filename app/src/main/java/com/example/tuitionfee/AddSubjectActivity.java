package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuitionfee.model.Subject;
import com.example.tuitionfee.remote.SubjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubjectActivity extends AppCompatActivity {
    SubjectService subjectService;
    Subject subjectAdd;
    Button btnCancel;
    Button btnAdd;
    EditText edtsubjectID;
    EditText edtsubjectName;
    EditText edtTuitionfee;
    EditText edtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtsubjectID = (EditText) findViewById(R.id.edtsubjectID);
        edtsubjectName = (EditText) findViewById(R.id.edtsubjectName);
        edtTuitionfee = (EditText) findViewById(R.id.edtTuitionfee);
        edtDescription = (EditText) findViewById(R.id.edtDescription);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageSubjectActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectId = edtsubjectID.getText().toString().trim();
                String subjectName = edtsubjectName.getText().toString().trim();
                Float tuitionFee = Float.parseFloat(edtTuitionfee.getText().toString().trim());
                String description = edtDescription.getText().toString().trim();
                subjectAdd.setSubjectid(subjectId);
                subjectAdd.setSubject(subjectName);
                subjectAdd.setDescription(description);
                subjectAdd.setTuitionfee(tuitionFee);
                addSubject(subjectAdd);
            }
        });

    }

    public void addSubject(Subject subject) {
        Call<Subject> call = subjectService.addSubject(subject);
        call.enqueue(new Callback<Subject>() {
            @Override
            public void onResponse(Call<Subject> call, Response<Subject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddSubjectActivity.this, "Subject created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Subject> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}