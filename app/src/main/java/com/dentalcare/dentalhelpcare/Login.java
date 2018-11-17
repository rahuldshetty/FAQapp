    package com.dentalcare.dentalhelpcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    public class Login extends AppCompatActivity {

    TextView pwd,emails;
    Button login;

    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emails=findViewById(R.id.loginEmail);
        pwd=findViewById(R.id.loginPass);
        login=findViewById(R.id.logLog);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.loginProgress);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emails.getText().toString();
                String pass=pwd.getText().toString();

                trylogin(email,pass);

            }
        });
    }

    void trylogin(String email,String pass){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent gotoMain=new Intent(Login.this,MainActivity.class);
                    startActivity(gotoMain);
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();

                }
                else{
                    Toast.makeText(Login.this,"Failed to login.",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }


            }
        });

    }

}
