package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuitionfee.model.Subject;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.SubjectService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageSubjectActivity extends AppCompatActivity {
    SubjectService subjectService;
    Button btnAdd;
    List<Subject> listSubject = new ArrayList<>();
    ListView listView;
    Button btnSearch;
    EditText edtSearch;
    Subject subjectFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subject);

        listView = (ListView) findViewById(R.id.lstSubject);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtSearch = (EditText) findViewById(R.id.edtsubjectID);
        subjectService = APIUtils.getSubjectService();

        getSubjectList();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSubjectActivity.class);
                startActivity(intent);
            }
        });

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSubject();
            }
        });
    }

    public void getSubjectList() {
        Call<List<Subject>> call = subjectService.getList();


        call.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                if (response.isSuccessful()) {
                    listSubject = response.body();
                    listView.setAdapter(new SubjectCustomListAdapter(ManageSubjectActivity.this, listSubject));
                    Toast.makeText(ManageSubjectActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {
                Toast.makeText(ManageSubjectActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSubject() {
        String id = edtSearch.getText().toString().trim();
        Call<Subject> call = subjectService.getSubjectByID(id);
        listSubject = null;

        call.enqueue(new Callback<Subject>() {
            @Override
            public void onResponse(Call<Subject> call, Response<Subject> response) {
                subjectFind = response.body();
                listSubject.add(subjectFind);
                listView.setAdapter(new SubjectCustomListAdapter(ManageSubjectActivity.this, listSubject));
                Toast.makeText(ManageSubjectActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Subject> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
