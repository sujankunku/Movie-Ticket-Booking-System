package com.example.movieticketbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmationPage extends AppCompatActivity implements Confirm1Fragment.SendMsg {

    Confirm2Frgament F2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        Button b1=(Button) findViewById(R.id.b1c);
        Button b2=(Button) findViewById(R.id.b2c);
        Bundle obj =getIntent().getExtras();
        String movname=obj.getString("Movie Name");
        String Theater=obj.getString("Theater");
        String Tickets=obj.getString("TicketCount");
        String Date=obj.getString("Date");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ConfirmationPage.this,BookingPage.class);
                i.putExtra("Movie Name",movname);
                i.putExtra("Theater",Theater);
                i.putExtra("TicketCount",Tickets);
                i.putExtra("Date",Date);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(ConfirmationPage.this,HomePage.class);
                startActivity(j);
            }
        });
    }

    @Override
    public void sendData(String message) {
        F2 = (Confirm2Frgament)  getSupportFragmentManager(). findFragmentById (R.id.fragmentContainerView2);
        assert F2 != null;
        F2.displayReceivedData(message);
    }
}
