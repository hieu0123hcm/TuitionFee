package com.example.tuitionfee.adminActivities.StudentManage;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.StudentService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private EditText edt_id_detail, edt_fullname_detail, edt_major_detail, edt_semester_detail;
    TextView edt_birthday_detail;
    private Button btnUpdate , btnDelete ;
    StudentService studentService ;
    Student student ;
    Date birthdayInput ;

    private String convertToLocalTime(String time) {
        SimpleDateFormat convertToLocalGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        String birthDateString = "";
        try {
            Date birthDate = convertToLocalGMT.parse(time);
            birthDateString = formatDate.format(birthDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return birthDateString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        studentService = APIUtils.getStudentService();

        mappingUI();

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("STUDENT");

        edt_id_detail.setText(student.getStudent_id());
        edt_fullname_detail.setText(student.getFullname());
        edt_major_detail.setText(student.getMajor());
        edt_semester_detail.setText(student.getSemester());
//       edt_birthday_detail.setText(student.getBirthday().toString());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  major , semester , fullname , birthDateString = "";

                major = edt_major_detail.getText().toString().trim();
                semester = edt_semester_detail.getText().toString().trim();
                fullname = edt_fullname_detail.getText().toString().trim();

                student.setMajor(major);
                student.setSemester(semester);
                student.setFullname(fullname);
                System.out.println(student.getBirthday());
//                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat formatDateString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//                try {
//                    Date birthdate = formatDate.parse(edt_birthday_detail.getText().toString().trim());
//                    birthDateString = formatDateString.format(birthdate);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                student.setBirthday(birthDateString);
                updateStudent(student);
            }
        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id = student.getStudent_id();
//                deleteStudent(id);
//            }
//        });
    }

    private void deleteStudent(String id) {
        Call<Void> call = studentService.deleteStudent(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(DetailActivity.this, StudentManagementActivity.class));
                    //Toast.makeText(DetailActivity.this,"Success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void updateStudent(Student student) {
        Call<Student> call = studentService.updateStudent(student);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    System.out.println(response.body().getFullname());
                    startActivity(new Intent(DetailActivity.this, StudentManagementActivity.class));
                    Toast.makeText(DetailActivity.this,"Update Successfully",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(DetailActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mappingUI(){
        edt_id_detail = findViewById(R.id.edt_id_detail);
        edt_fullname_detail = findViewById(R.id.edt_fullname_detail);
        edt_major_detail = findViewById(R.id.edt_major_detail);
        edt_semester_detail = findViewById(R.id.edt_semester_detail);
       // edt_birthday_detail = findViewById(R.id.edt_birthday_detail);

//        edt_birthday_detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    pickDate();
//            }
//        });
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

    }
    private void pickDate(){
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String date = year + "-" + month + "-" + dayOfMonth ;
                edt_birthday_detail.setText(date);
            }
        },2017,01,01);
        datePickerDialog.show();

    }

    private void confirmAddingDialog(){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Notification");
        aBuilder.setMessage("Adding Succesfully");
        aBuilder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DetailActivity.this, StudentManagementActivity.class);
                startActivity(intent);
            }
        });
        aBuilder.show();
    }



}