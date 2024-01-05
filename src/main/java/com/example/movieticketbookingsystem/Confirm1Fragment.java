package com.example.movieticketbookingsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


public class Confirm1Fragment extends Fragment {
    RadioGroup rg1;
    RadioButton rb1,rb2;
    Button b1;
    SendMsg SM;

    public Confirm1Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v1 = inflater.inflate(R.layout.fragment_confirm1, container,false);
        rg1=(RadioGroup) v1.findViewById(R.id.rg11);
        rb1=(RadioButton) v1.findViewById(R.id.rb1);
        rb2=(RadioButton) v1.findViewById(R.id.rb2);
        b1=(Button) v1.findViewById(R.id.Add);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=rg1.getCheckedRadioButtonId();
                String s="";
                if(id==rb1.getId()){
                    s+=rb1.getText().toString();
                }else if(id==rb2.getId()) {
                    s+=rb2.getText().toString();
                }
                String msg=s.toString();
                SM = (SendMsg) getActivity();
                SM.sendData(msg);
            }
        });
        return v1;
    }
    interface SendMsg
    {
        void sendData(String message);

    }
}