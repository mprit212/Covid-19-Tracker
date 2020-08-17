package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    private  int poistiondistrict;
    public String DISTRICKNAME;
    public  String UPDATETIME;
    TextView tvdistrict,tvactive,tvtotalcase,tvdead,tvrecov,tvupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Intent intent=getIntent();
        poistiondistrict=intent.getIntExtra("position",0);
        DISTRICKNAME=intent.getStringExtra("districtname");

        SharedPreferences preferences=getSharedPreferences("Updatetime",MODE_PRIVATE);
        UPDATETIME=preferences.getString("updatetime","");

        getSupportActionBar().setTitle("Details of "+ DISTRICKNAME+"");


        tvdistrict=findViewById(R.id.tvdistict);
        tvactive=findViewById(R.id.tvdisActiveCase);
        tvtotalcase=findViewById(R.id.tvdistotalcase);
        tvdead=findViewById(R.id.tvdisdeads);
        tvrecov=findViewById(R.id.tvdisrecoverd);
        tvupdate=findViewById(R.id.tvlastupdate2);

        tvdistrict.setText(Main5Activity.distrtictarrylist.get(poistiondistrict).getDistrict());
        tvactive.setText(Main5Activity.distrtictarrylist.get(poistiondistrict).getActive());
        tvtotalcase.setText(Main5Activity.distrtictarrylist.get(poistiondistrict).getConfirmed());
        tvdead.setText(Main5Activity.distrtictarrylist.get(poistiondistrict).getDeceased());
        tvrecov.setText(Main5Activity.distrtictarrylist.get(poistiondistrict).getRecoverd());
        tvupdate.setText("Last Update: "+UPDATETIME);
    }
}
