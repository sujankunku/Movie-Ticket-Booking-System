package com.example.movieticketbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class SignupPage extends AppCompatActivity {
    SQLiteDatabase SignupDB=null;
    String detaillist=" ";
    EditText t1,t2;
    Button Add,GetDet,Del,Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        createDB();
        t1=(EditText) findViewById(R.id.et1sp);
        t2=(EditText) findViewById(R.id.et2sp);
        Add=(Button) findViewById(R.id.b1sp);
        GetDet=(Button) findViewById(R.id.b2sp);
        Del=(Button) findViewById(R.id.b3sp);
        Register=(Button) findViewById(R.id.b4sp);
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
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignupPage.this,LoginActivity.class);
                i.putExtra("uname",t1.getText().toString());
                i.putExtra("password",t2.getText().toString());
                startActivity(i);
            }
        });

    }

    public void createDB(){
        try{
            SignupDB=this.openOrCreateDatabase("SignupDetails",MODE_PRIVATE,null);
            SignupDB.execSQL("CREATE TABLE IF NOT EXISTS SignUpDetails(UserName varchar,Password varchar);");
            File database = getApplicationContext().getDatabasePath("SignupDetails");
            if (database.exists()){
                Toast.makeText(this,"Database created Successfully"+database.toString(),Toast.LENGTH_LONG).show();}
        }
        catch(Exception e) {
            Log.e("CONTACTS ERROR ", "DATABASE CREATION ERROR");
        }
    }

    public void addDetails(View view){
        String Uname=t1.getText().toString();
        String Password=t2.getText().toString();
        SignupDB.execSQL("Insert into SignupDetails (UserName,Password) values ('"+Uname+"','"+Password+"');");
        Toast.makeText(this, " details have been added", Toast.LENGTH_LONG).show();
    }

    public void getDetails(View view){
        Cursor cursor= SignupDB.rawQuery("select * from SignupDetails",null);
        int unamecolumn=cursor.getColumnIndex ("UserName");
        int passcolumn=cursor.getColumnIndex("Password");
        cursor.moveToFirst();
        if ((cursor!=null)&& (cursor.getCount()>0)){
            do{
                String uname=cursor.getString(unamecolumn);
                String pass=cursor.getString(passcolumn);
                detaillist=detaillist+ "\n"+uname+"\n"+pass+"\n";
            }while(cursor.moveToNext());
            Toast.makeText(this,"Details"+detaillist, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"No data Record", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteDetails(View view) {
        String uname = t1.getText().toString();
        SignupDB.execSQL("Delete from LoginDetails where UserName = '" + uname + "';");
        Toast.makeText(this, uname + " details have been deleted", Toast.LENGTH_LONG).show();
    }
}