package com.example.movieticketbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.*;
import android.widget.Toast;

import android.os.Bundle;


public class LoginActivity extends AppCompatActivity {

    EditText t1,t2;
    Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t1 = findViewById(R.id.uname);
        t2 = findViewById(R.id.password);
        LoginButton = findViewById(R.id.loginbutton);
        ImageView image=(ImageView) findViewById(R.id.imageview2);
        image.setImageResource(R.drawable.movieimage0);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle obj=getIntent().getExtras();
                String uname=obj.getString("uname");
                String password=obj.getString("password");
                SharedPreferences pref=getApplicationContext().getSharedPreferences("mypref",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("Uname",uname);
                editor.putString("password",password);
                editor.commit();
                String s1=pref.getString("Uname",null);
                String s2=pref.getString("password",null);
                //addDetails(v);
               // getDetails(v);
                String username = t1.getText().toString().trim();
                String pass = t2.getText().toString().trim();
                if(username.equals(s1)&&pass.equals(s2)){
                    Toast.makeText(LoginActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,HomePage.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}


