package com.dentalcare.dentalhelpcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartScreen extends AppCompatActivity {

    Button regBtn,logBtn;
    ImageView faqBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regBtn=findViewById(R.id.btnReg);
        logBtn=findViewById(R.id.btnLogin);
        faqBtn=findViewById(R.id.btnFAQ);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoReg=new Intent(StartScreen.this,Register.class);
                startActivity(gotoReg);
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin=new Intent(StartScreen.this,Login.class);
                startActivity(gotoLogin);
            }
        });

    }
}
