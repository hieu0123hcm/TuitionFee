package com.example.tuitionfee.remote;

import com.example.tuitionfee.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {
    @GET("student/")
    Call<List<Student>> getAllStudent();

    @PUT("student/update")
    Call<Student> updateStudent( @Body Student student);


    @DELETE("student/{student_id}")
    Call<Void> deleteStudent(@Path("student_id") String id);

    @GET("student/{account_id}")
    Call<Student> getStudentByAccID(@Path("account_id") String id);
}
