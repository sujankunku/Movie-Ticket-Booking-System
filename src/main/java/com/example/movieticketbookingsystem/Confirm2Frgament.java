package com.example.movieticketbookingsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


public class Confirm2Frgament extends Fragment {
    EditText t1;
    TextView tv1;
    Button b1,b2;
    int count=0;
    int price=0;


    public Confirm2Frgament() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v1 = inflater.inflate(R.layout.fragment_confirm2_frgament, container, false);
        t1=(EditText) v1.findViewById(R.id.ticketcount);
        tv1=(TextView) v1.findViewById(R.id.tv33);
        return v1;
    }

    protected void displayReceivedData(String message) {
        count=Integer.parseInt(t1.getText().toString());
        if(message.equals("Class A")){
            price+=count*110;
            tv1.setText(String.valueOf(price));
        }
        else
            price+=count*90;
            tv1.setText(String.valueOf(price));
    }
}