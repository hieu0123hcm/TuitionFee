package com.example.tuitionfee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuitionfee.adminActivities.AdminMenuActivity;
import com.example.tuitionfee.model.Account;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.AccountService;
import com.example.tuitionfee.studentActivities.StudentMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_email,edt_password ;
    private Button btnLogin ;
    private FirebaseAuth mAuth;
    private TextView tv_forgot;


    AccountService accountService ;
    Account account ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        tv_forgot = findViewById(R.id.tv_forgot);

        accountService  = APIUtils.getAccountService();

        btnLogin = findViewById(R.id.btnLogin);
        mAuth =FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uerLogin();
            }
        });

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });
    }

    private void uerLogin() {
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        if(email.isEmpty()){
            edt_email.setError("Email is required !");
            edt_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Please enter an email !");
            edt_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edt_password.setError("Password is required !");
            edt_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    getAccountByID(user.getUid());
                    Log.d(null,user.getUid());

                }else{
                    Toast.makeText(LoginActivity.this,"Invalid email or password",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void getAccountByID(String uid) {
        Call<Account> call = accountService.getAccount(uid);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if ( response.isSuccessful()){
                    account = response.body();
                    if(account.getRole().equals("admin")){
                        Intent intent = new Intent(LoginActivity.this, AdminMenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", uid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                    else if (account.getRole().equals("student")){
                        Intent intent = new Intent(LoginActivity.this, StudentMenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", uid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d(null,t.getMessage());
            }
        });
    }


}