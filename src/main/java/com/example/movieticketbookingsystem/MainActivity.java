package com.example.movieticketbookingsystem;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.content.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button) findViewById(R.id.b11);
        Button b2=(Button) findViewById(R.id.b12);
        ImageView image=(ImageView)findViewById(R.id.imageView);
        ImageView image2=(ImageView) findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.movieimage2);
        image2.setImageResource(R.drawable.movieimage4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(MainActivity.this,SignupPage.class);
                startActivity(j);
            }
        });
    }
}
