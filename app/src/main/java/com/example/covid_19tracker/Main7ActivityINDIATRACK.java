package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class Main7ActivityINDIATRACK extends AppCompatActivity {

    TextView tvindCases,tvindRecoverd,tvindCritical,tvindActive,tvindTodaycases,tvindTotaldeaths,tvindTodaydeaths,tvcasespermil,tvdeathspermil,tvtestspermil,tvtotaltests;
    SimpleArcLoader simpleArcLoaderind;
    ScrollView scrollViewind;
    PieChart pieChartind;
    public String URL2="https://www.mygov.in/covid-19";
    public String URL3="https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7_indiatrack);

        getSupportActionBar().setTitle("COVID-19 India Tracker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pieChartind=findViewById(R.id.piechart2);
        scrollViewind=findViewById(R.id.scrollindia);
        simpleArcLoaderind=findViewById(R.id.loader789);

        tvindCases=findViewById(R.id.tvindiacases);
        tvindRecoverd=findViewById(R.id.tvindiarecoverd);
        tvindCritical=findViewById(R.id.tvindiacritical);
        tvindActive=findViewById(R.id.tvindiaactive);
        tvindTodaycases=findViewById(R.id.tvindiatodaycases);
        tvindTotaldeaths=findViewById(R.id.tvindiatotaldeaths);
        tvindTodaydeaths=findViewById(R.id.tvindiatodaydeaths);
        tvcasespermil=findViewById(R.id.tvindiacasespermil);
        tvdeathspermil=findViewById(R.id.tvindiadeathspermil);
        tvtestspermil=findViewById(R.id.tvindiatestsspermil);
        tvtotaltests=findViewById(R.id.tvindiatotaltests);

        fetchdata();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id)
        {
            case R.id.item1 :
                startActivity(new Intent(this,Main2Activity.class));
                return true;

            case R.id.item2 :
                Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL2));
                if (openUrlIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(openUrlIntent);
                }

                return true;

            case R.id.item3 :
                Intent openUrlIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(URL3));
                if (openUrlIntent2.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(openUrlIntent2);
                }
                return  true;

                default:
                    return super.onOptionsItemSelected(item);

        }


    }

    private void fetchdata()
    {
        String url="https://corona.lmao.ninja/v2/countries/India/";

        simpleArcLoaderind.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());

                    tvindCases.setText(jsonObject.getString("cases"));
                    tvindRecoverd.setText(jsonObject.getString("recovered"));
                    tvindCritical.setText(jsonObject.getString("critical"));
                    tvindActive.setText(jsonObject.getString("active"));
                    tvindTodaycases.setText(jsonObject.getString("todayCases"));
                    tvindTotaldeaths.setText(jsonObject.getString("deaths"));
                    tvindTodaydeaths.setText(jsonObject.getString("todayDeaths"));
                    tvcasespermil.setText(jsonObject.getString("casesPerOneMillion"));
                    tvdeathspermil.setText(jsonObject.getString("deathsPerOneMillion"));
                    tvtestspermil.setText(jsonObject.getString("testsPerOneMillion"));
                    tvtotaltests.setText(jsonObject.getString("tests"));


                    pieChartind.addPieSlice(new PieModel("Cases",Integer.parseInt(tvindCases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChartind.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvindRecoverd.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChartind.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvindTotaldeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChartind.addPieSlice(new PieModel("Active",Integer.parseInt(tvindActive.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChartind.startAnimation();

                    simpleArcLoaderind.stop();
                    simpleArcLoaderind.setVisibility(View.GONE);
                    scrollViewind.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoaderind.stop();
                    simpleArcLoaderind.setVisibility(View.GONE);
                    scrollViewind.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main7ActivityINDIATRACK.this,error.getMessage(),Toast.LENGTH_LONG).show();
                simpleArcLoaderind.stop();
                simpleArcLoaderind.setVisibility(View.GONE);
                scrollViewind.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void gototrackindia(View view) {

        startActivity(new Intent(this,MainActivitydemo1.class));
    }


}
