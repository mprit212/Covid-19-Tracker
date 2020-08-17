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

public class MainActivity extends AppCompatActivity {

    TextView tvCases,tvRecoverd,tvCritical,tvActive,tvTodaycases,tvTotaldeaths,tvTodaydeaths,tvAffedcountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    public String URL2="https://www.mygov.in/covid-19";
    public String URL3="https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("COVID-19 Global Tracker");
        tvCases=findViewById(R.id.tvcases);
        tvRecoverd=findViewById(R.id.tvrecoverd);
        tvCritical=findViewById(R.id.tvcritical);
        tvActive=findViewById(R.id.tvactive);
        tvTodaycases=findViewById(R.id.tvtodaycases);
        tvTotaldeaths=findViewById(R.id.tvtotaldeaths);
        tvTodaydeaths=findViewById(R.id.tvtodaydeaths);
        tvAffedcountries=findViewById(R.id.tvaffedcountries);

        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollstates);
        pieChart=findViewById(R.id.piechart);

        fetchdata();
    }

    private void fetchdata()
    {
        String url="https://corona.lmao.ninja/v2/all/ ";

        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());

                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecoverd.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTodaycases.setText(jsonObject.getString("todayCases"));
                    tvTotaldeaths.setText(jsonObject.getString("deaths"));
                    tvTodaydeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffedcountries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvRecoverd.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotaldeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void gototrackcountry(View view) {

        startActivity(new Intent(this,Main7ActivityINDIATRACK.class));
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
}
