package com.example.movieticketbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView t1,t2,t3;
    EditText et1;
    Spinner s1,s2;
    String [] Movies={"SpiderMan","BatMan","Fast And Furios 10","Bahubali","RRR"};
    String [] Theater={"Inox","PVR","Vishnu","Prasad IMAX"};

    private ImageView mImageView;
    private int mScreenWidth;
    private int mImageCount = 5;
    private int mCurrentImageIndex = 0;

    SQLiteDatabase MovieDB=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button Add,GetDet,Del,Confirm,Homepage;
        t1=(TextView) findViewById(R.id.tv3);
        t2=(TextView) findViewById(R.id.tv4);
        t3=(TextView) findViewById(R.id.tv5);
        et1=(EditText)findViewById(R.id.et1);
        s1=(Spinner) findViewById(R.id.sp1);
        s2=(Spinner) findViewById(R.id.sp2);
        CalendarView cv1=(CalendarView)findViewById(R.id.cv1);
        Add=(Button)findViewById(R.id.b1);
        GetDet=(Button)findViewById(R.id.b2);
        Del=(Button)findViewById(R.id.b3);
        Confirm=(Button)findViewById(R.id.b4);
        //Homepage=(Button)findViewById(R.id.b5);
        String count=et1.getText().toString();

        s1.setOnItemSelectedListener(this);
        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,Movies);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(ad);
        s2.setOnItemSelectedListener(this);
        ArrayAdapter ad1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,Theater);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(ad1);
        createDB();

        cv1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int DateOfMonth, int Month, int Year) {
                String d1=DateOfMonth + "-" + Month + "-" + Year;
                t3.setText(d1);
            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t2.setText(Theater[position]);
                // do something with the selected item from spinner1
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDetails(view);
            }
        });
        GetDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDetails(view);
            }
        });
        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDetails(view);
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(HomePage.this,ConfirmationPage.class);
                j.putExtra("Movie Name",t1.getText().toString());
                j.putExtra("Theater",t2.getText().toString());
                j.putExtra("TicketCount",et1.getText().toString());
                j.putExtra("Date",t3.getText().toString());
                startActivity(j);
            }
        });
        mImageView = findViewById(R.id.imageView);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        // Set the initial image
        mImageView.setImageResource(R.drawable.spiderman);

        // Start the animation
        animateImage();
    }

    private void animateImage() {
        // Calculate the starting and ending X position for the animation
        int startX = mCurrentImageIndex * mScreenWidth;
        int endX = (mCurrentImageIndex + 1) * mScreenWidth;

        // Create the animator object
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "translationX", startX, endX);
        animator.setDuration(600);
        animator.setInterpolator(new LinearInterpolator());

        // Set up the animator listener
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Move to the next image
                mCurrentImageIndex++;
                if (mCurrentImageIndex >= mImageCount) {
                    mCurrentImageIndex = 0;
                }

                // Set the new image
                switch (mCurrentImageIndex) {
                    case 0:
                        mImageView.setImageResource(R.drawable.spiderman);
                        break;
                    case 1:
                        mImageView.setImageResource(R.drawable.batman);
                        break;
                    case 2:
                        mImageView.setImageResource(R.drawable.fastandfurious);
                        break;
                    case 3:
                        mImageView.setImageResource(R.drawable.bahubali);
                        break;
                    case 4:
                        mImageView.setImageResource(R.drawable.rrr);
                        break;
                }

                // Start the next animation
                animateImage();
            }
        });

        // Start the animation
        animator.start();
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        t1.setText(Movies[i]);
        //t2.setText(Theater[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createDB(){
        try{
            MovieDB=this.openOrCreateDatabase("MovieDetails",MODE_PRIVATE,null);
            MovieDB.execSQL("CREATE TABLE IF NOT EXISTS MovieDetails(MovName varchar,TheaterName varchar,TicketCount Varchar,Date varchar);");
            File database = getApplicationContext().getDatabasePath("MovieDetails");
            if (database.exists()){
                Toast.makeText(this,"Database created Successfully"+database.toString(),Toast.LENGTH_LONG).show();}
        }
        catch(Exception e) {
            Log.e("CONTACTS ERROR ", "DATABASE CREATION ERROR");
        }
    }

    public void addDetails(View view){
        String Movname=t1.getText().toString();
        String TheaterName=t2.getText().toString();
        String TicketCount=et1.getText().toString();
        String Date=t3.getText().toString();
        MovieDB.execSQL("Insert into MovieDetails (MovName,TheaterName,TicketCount,Date) values ('"+Movname+"','"+TheaterName+"','"+TicketCount+"','"+Date+"');");
        Toast.makeText(this, " details have been added", Toast.LENGTH_LONG).show();
    }

    public void getDetails(View view){
        Cursor cursor= MovieDB.rawQuery("select * from MovieDetails",null);
        int Movnamecolumn=cursor.getColumnIndex ("MovName");
        int Theatercolumn=cursor.getColumnIndex("TheaterName");
        int TicketCount=cursor.getColumnIndex("TicketCount");
        int Date=cursor.getColumnIndex("Date");
        cursor.moveToFirst();
        String detaillist="";
        if ((cursor!=null)&& (cursor.getCount()>0)){
            do{
                String Movname=cursor.getString(Movnamecolumn);
                String Theatername=cursor.getString(Theatercolumn);
                String Ticketcount=cursor.getString(TicketCount);
                String date=cursor.getString(Date);
                detaillist=detaillist+ "\n"+Movname+"\n"+Theatername+"\n"+Ticketcount+"\n"+date+"\n";
            }while(cursor.moveToNext());
            Toast.makeText(this,"Details"+detaillist, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"No data Record", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteDetails(View view) {
        String Movname = t1.getText().toString();
        MovieDB.execSQL("Delete from MovieDetails where MovName = '" + Movname + "';");
        Toast.makeText(this, Movname + " details have been deleted", Toast.LENGTH_LONG).show();
    }
}