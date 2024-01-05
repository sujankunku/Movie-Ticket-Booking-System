package com.example.movieticketbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        Button b1=(Button) findViewById(R.id.B1b);
        Button b2=(Button) findViewById(R.id.b2b);
        TextView t1=(TextView) findViewById(R.id.tv1b);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle obj =getIntent().getExtras();
                String movname=obj.getString("Movie Name");
                String Theater=obj.getString("Theater");
                String Tickets=obj.getString("TicketCount");
                String Date=obj.getString("Date");
                String a="Tickets Booked Successfully";
                t1.setText(a+"\n"+movname+"\n"+Theater+"\n"+Tickets+"\n"+Date);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(BookingPage.this,HomePage.class);
                startActivity(i);
            }
        });
    }
}