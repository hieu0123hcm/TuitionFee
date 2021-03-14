package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuitionfee.adapter.StudyingCustomListAdapter;
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
    String UID;
    String studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying);

        listView = (ListView) findViewById(R.id.studyingListView);
        studyingService = APIUtils.getStudyingService();


        Bundle extras = getIntent().getExtras();
        studentID = extras.getString("studentID");
        UID = extras.getString("key");

        getStudyingList(studentID);

    }

    public void getStudyingList(String studentID){
        Call<List<Studying>> call = studyingService.getStudyingByStuIDAndStatus(studentID, "failed");
        call.enqueue(new Callback<List<Studying>>() {
            @Override
            public void onResponse(Call<List<Studying>> call, Response<List<Studying>> response) {
                if(response.isSuccessful()){
                    studyingList = response.body();
                    if(studyingList != null){

                        listView.setAdapter(new StudyingCustomListAdapter(StudyingActivity.this, studyingList));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(StudyingActivity.this, StudyingItemActivity.class);
                                intent.putExtra("account_id", UID);
                                intent.putExtra("studentID",studentID );
                                System.out.println(UID);
                                intent.putExtra("studying_id", String.valueOf(response.body().get(position).getId()));
                                intent.putExtra("subject_id", response.body().get(position).getSubjectID());
                                 intent.putExtra("semesterno", response.body().get(position).getSemesterNo());
                                startActivity(intent);
                            }
                        });
                        Toast.makeText(StudyingActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Studying>> call, Throwable t) {
                Toast.makeText(StudyingActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}