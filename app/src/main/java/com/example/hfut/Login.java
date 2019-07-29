package com.example.hfut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private Button longinBootn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        longinBootn=(Button)findViewById(R.id.loginButton);
        longinBootn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent it=new Intent(getApplicationContext(),MainActivity.class);//启动MainActivity
        startActivity(it);
    }
}
