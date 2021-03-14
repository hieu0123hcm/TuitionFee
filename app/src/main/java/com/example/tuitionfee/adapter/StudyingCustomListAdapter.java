package com.example.tuitionfee.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuitionfee.R;
import com.example.tuitionfee.StudyingItemActivity;
import com.example.tuitionfee.model.Studying;

import java.util.List;

public class StudyingCustomListAdapter extends BaseAdapter {
    private Context context;
    private List<Studying> studyingList;

    public StudyingCustomListAdapter(Context context, List<Studying> studyingList) {
        this.context = context;
        this.studyingList = studyingList;
    }

    @Override
    public int getCount() {
        return studyingList.size();
    }

    @Override
    public Studying getItem(int position) {
        return studyingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studyingList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.studying_list_items, parent, false);
        }
            Studying currentStudying = (Studying) getItem(position);

            TextView tvSubjectID = (TextView) convertView.findViewById(R.id.studyingSubjectID);
            TextView tvSubject = (TextView) convertView.findViewById(R.id.studyingSubject);
            TextView  tvSemesterNo = (TextView) convertView.findViewById(R.id.studyingSemesterNo);

            tvSubjectID.setText(  currentStudying.getSubjectID());
            tvSubject.setText(currentStudying.getSubject());
            tvSemesterNo.setText("semester " +String.valueOf(currentStudying.getSemesterNo()));

//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, StudyingItemActivity.class);
//                    intent.putExtra("studying_id", String.valueOf(currentStudying.getId()));
//                    intent.putExtra("subject_id", currentStudying.getSubjectID());
//                    intent.putExtra("semesterno", currentStudying.getSemesterNo());
//
//                    context.startActivity(intent);
//                }
//            });

            return convertView;
    }
}
