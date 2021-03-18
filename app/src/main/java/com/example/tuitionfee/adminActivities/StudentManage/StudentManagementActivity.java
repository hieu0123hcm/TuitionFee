package com.example.tuitionfee.adminActivities.StudentManage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.LoginActivity;
import com.example.tuitionfee.R;
import com.example.tuitionfee.adapter.StudentAdapter;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.StudentService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentManagementActivity extends AppCompatActivity {
    StudentService studentService ;
    List<Student> studentList = new ArrayList<>();
    ListView listView ;
    StudentAdapter adapter ;
    Button btnLogout ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management_menu);

        listView = findViewById(R.id.lv_student);
        studentService = APIUtils.getStudentService();

//        btnLogout =findViewById(R.id.btnLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(StudentManagementActivity.this, LoginActivity.class));
//            }
//        });

        getAllStudent();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentDTO = studentList.get(position);
                Intent intent = new Intent(StudentManagementActivity.this, DetailActivity.class);
                intent.putExtra("STUDENT", studentDTO);
                startActivity(intent);
            }
        });


    }

    private void getAllStudent() {
        Call<List<Student>> call = studentService.getAllStudent();
        call.enqueue((new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()){
                    studentList = response.body();
                    listView.setAdapter(new StudentAdapter(StudentManagementActivity.this,studentList));
                    Toast.makeText(StudentManagementActivity.this,"Success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(StudentManagementActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new StudentAdapter(this, studentList);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


}