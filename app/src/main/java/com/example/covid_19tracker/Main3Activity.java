package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private  int poistioncountry;
    TextView TVCOUNTRY,TVCASES,TVTODAYCASES,TVRECOVERED,TVCRITICAL,TVACTIVE,TVDEATHS,TVTODAYDEATHS,tvcasepermil,tvdeathspermil,tvtestpermil,tvtotoaltest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=getIntent();
        poistioncountry=intent.getIntExtra("pos",0);

        getSupportActionBar().setTitle("Details of " + Main2Activity.countrymodelList.get(poistioncountry).getCountry() );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        TVCOUNTRY=findViewById(R.id.tvcounty);
        TVCASES=findViewById(R.id.tvtotalcase);
        TVTODAYCASES=findViewById(R.id.tvtodaycase);
        TVRECOVERED=findViewById(R.id.tvrecoverds);
        TVCRITICAL=findViewById(R.id.tvcritical);
        TVACTIVE=findViewById(R.id.tvactive);
        TVDEATHS=findViewById(R.id.tvdeaths);
        TVTODAYDEATHS=findViewById(R.id.tvtodaydeath);
        tvcasepermil=findViewById(R.id.tvcasespermil);
        tvdeathspermil=findViewById(R.id.tvdeathspermil);
        tvtestpermil=findViewById(R.id.tvtestsspermil);
        tvtotoaltest=findViewById(R.id.tvtotaltests123);


        TVCOUNTRY.setText(Main2Activity.countrymodelList.get(poistioncountry).getCountry());
        TVCASES.setText(Main2Activity.countrymodelList.get(poistioncountry).getCases());
        TVTODAYCASES.setText(Main2Activity.countrymodelList.get(poistioncountry).getTodaycases());
        TVRECOVERED.setText(Main2Activity.countrymodelList.get(poistioncountry).getRecoverd());
        TVCRITICAL.setText(Main2Activity.countrymodelList.get(poistioncountry).getCritical());
        TVACTIVE.setText(Main2Activity.countrymodelList.get(poistioncountry).getActive());
        TVDEATHS.setText(Main2Activity.countrymodelList.get(poistioncountry).getDeaths());
        TVTODAYDEATHS.setText(Main2Activity.countrymodelList.get(poistioncountry).getTodaydeaths());
        tvcasepermil.setText(Main2Activity.countrymodelList.get(poistioncountry).getCasepermill());
        tvdeathspermil.setText(Main2Activity.countrymodelList.get(poistioncountry).getDeathpermill());
        tvtestpermil.setText(Main2Activity.countrymodelList.get(poistioncountry).getTestpermill());
        tvtotoaltest.setText(Main2Activity.countrymodelList.get(poistioncountry).getTotaltest());
    }
}
