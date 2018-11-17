package com.dentalcare.dentalhelpcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore databaseRef;

    EditText email,pass,name,code;
    Button reg;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.reg_email);
        pass=findViewById(R.id.reg_pass);
        name=findViewById(R.id.reg_name);
        code=findViewById(R.id.reg_code);
        reg=findViewById(R.id.reg_reg);

        mAuth=FirebaseAuth.getInstance();
        databaseRef=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.regProgress);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names=name.getText().toString();
                String emails=email.getText().toString();
                String pwd=pass.getText().toString();
                String codes=code.getText().toString();

                createUser(emails,pwd,codes,names);


            }
        });


    }

    void createUser(final String email,final String pass, final String codes,final String names){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser mCurrentUser=mAuth.getCurrentUser();
                    Map<String,String> hash=new HashMap<String,String>();
                    hash.put("name",names);
                    hash.put("code",codes);
                    hash.put("email",email);


                   databaseRef.collection("App").document("Users").collection(mCurrentUser.getUid()).add(hash).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                       @Override
                         public void onComplete(@NonNull Task<DocumentReference> task1) {
                            if(task1.isSuccessful())
                            {

                                Intent gotoMain=new Intent(Register.this,MainActivity.class);
                                startActivity(gotoMain);
                                progressBar.setVisibility(View.INVISIBLE);
                                finish();

                            }
                            else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Register.this,"Failed to Register",Toast.LENGTH_LONG).show();
                            }

                       }
                   });

                }
                else{
                    Toast.makeText(Register.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
