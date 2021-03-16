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

public class AddSubjectActivity extends AppCompatActivity {
    SubjectService subjectService;

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
                Long tuitionFee = Long.parseLong("0");;
                try {

                    tuitionFee = Long.parseLong(edtTuitionfee.getText().toString().trim());
                }catch ( Exception e){
                    Toast.makeText(AddSubjectActivity.this, "Please input right format!", Toast.LENGTH_SHORT).show();
                }
                String description = edtDescription.getText().toString().trim();

                Subject subjectAdd = new Subject();
                subjectAdd.setSubjectid(subjectId);
                subjectAdd.setSubject(subjectName);
                subjectAdd.setDescription(description);
                subjectAdd.setTuitionFee(tuitionFee);
                System.out.println(subjectAdd);

                if(subjectService == null){
                    subjectService = APIUtils.getSubjectService();

                }
                Call<Subject> call = subjectService.addSubject(subjectAdd);
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
        });

    }

}