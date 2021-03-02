package com.example.tuitionfee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StudyingItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying_item);
        TextView txtStudyingID = findViewById(R.id.txtStudyingID);
        TextView txtSubjectID = findViewById(R.id.txtSubjectID);

        Bundle extras = getIntent().getExtras();
        String studyingID = extras.getString("studying_id");
        String subjectID = extras.getString("subject_id");



        txtStudyingID.setText(studyingID);
        txtSubjectID.setText(subjectID);
    }
}