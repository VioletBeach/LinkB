package com.example.rinkb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final Button btnSignUp = (Button)findViewById(R.id.btnSignUp);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        final RadioButton rp = (RadioButton)findViewById(R.id.radioPrivate);
        final RadioButton rc = (RadioButton)findViewById(R.id.radiCcorporate);
        final Button btnBack = (Button)findViewById(R.id.btnBack);
        final Button btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),main.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int privCorp = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(privCorp);
                if(rb.getText().toString()==rp.getText().toString()) {
                    Intent intent = new Intent(getApplicationContext(), privateSignUp.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), coprateSignup.class);
                    startActivity(intent);
                }
            }
        });
    }
}