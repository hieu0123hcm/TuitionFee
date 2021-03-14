package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Student> listStudent;

    public StudentAdapter(@NonNull Context context, @NonNull List<Student> listStudent) {
        this.context = context;
        this.listStudent = listStudent;
    }

    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.student_list,null);
            
            viewHolder = new ViewHolder();
            viewHolder.tvStudentId = convertView.findViewById(R.id.tv_id);
            viewHolder.tvMajor = convertView.findViewById(R.id.tv_major);
            viewHolder.tvSemester = convertView.findViewById(R.id.tv_semester);
            viewHolder.tvFullName = convertView.findViewById(R.id.tv_fullname);
            viewHolder.tvBirthday = convertView.findViewById(R.id.tv_birthday);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student studentDTO = listStudent.get(position);
        viewHolder.tvStudentId.setText(studentDTO.getStudent_id());
        viewHolder.tvMajor.setText(studentDTO.getMajor());
        viewHolder.tvSemester.setText(studentDTO.getSemester());
        viewHolder.tvFullName.setText(studentDTO.getFullname());

        SimpleDateFormat convertToLocalGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        String birthDateString = "";
        try {
            Date birthDate = convertToLocalGMT.parse(studentDTO.getBirthday().toString());
            birthDateString = formatDate.format(birthDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.tvBirthday.setText(birthDateString);

        return convertView ;
    }

    public class ViewHolder {
        private TextView tvStudentId;
        private TextView tvMajor;
        private TextView tvSemester;
        private TextView tvFullName;
        private TextView tvBirthday;

    }
}
