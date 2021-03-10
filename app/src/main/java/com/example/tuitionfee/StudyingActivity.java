package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying);

        listView = (ListView) findViewById(R.id.studyingListView);
        studyingService = APIUtils.getStudyingService();


        getStudyingList();

    }

    public void getStudyingList(){
        Call<List<Studying>> call = studyingService.getStudyingByStuIDAndStatus("SE140002", "failed");
        call.enqueue(new Callback<List<Studying>>() {
            @Override
            public void onResponse(Call<List<Studying>> call, Response<List<Studying>> response) {
                if(response.isSuccessful()){
                    studyingList = response.body();
                    if(studyingList != null){

                        listView.setAdapter(new StudyingCustomListAdapter(StudyingActivity.this, studyingList));
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