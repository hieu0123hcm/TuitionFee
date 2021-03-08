package com.example.tuitionfee;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingItemActivity;

import com.example.tuitionfee.SubjectItemActivity;
import com.example.tuitionfee.model.Subject;


import java.util.List;

public class SubjectCustomListAdapter extends BaseAdapter {
    private Context context;
    private List<Subject> subjectList;

    public SubjectCustomListAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Subject getItem(int position) {
        return subjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.subject_list_adapter, parent, false);
        }
        Subject currentSubject = (Subject) getItem(position);

        TextView tvSubjectName = (TextView) convertView.findViewById(R.id.subjectName);
        TextView tvSubjectID = (TextView) convertView.findViewById(R.id.subjectID);

        tvSubjectName.setText(currentSubject.getSubject());
        System.out.println("Test" + currentSubject.getSubject());
        tvSubjectID.setText("Subject ID: " + String.valueOf(currentSubject.getSubjectid()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubjectItemActivity.class);
                intent.putExtra("subject", currentSubject.getSubject());
                intent.putExtra("subjectid", currentSubject.getSubjectid());
                intent.putExtra("tuitionfee",String.valueOf(currentSubject.getTuitionfee()));
                intent.putExtra("description", currentSubject.getDescription());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
