package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    EditText edtSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;


    public  static List<countrymodel>  countrymodelList= new ArrayList<>();
    countrymodel countrymodel;
    CustAdapter custAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtSearch=findViewById(R.id.edtsearch);
        listView=findViewById(R.id.ListView);
        simpleArcLoader=findViewById(R.id.loader);


        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchdata();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                startActivity(new Intent(getApplicationContext(),Main3Activity.class).putExtra("pos",position));
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                custAdapter.getFilter().filter(s);
                custAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void fetchdata()
    {
        String url="https://corona.lmao.ninja/v2/countries/ ";

        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String CountryName= jsonObject.getString("country");
                        String cases= jsonObject.getString("cases");
                        String todaycases= jsonObject.getString("todayCases");
                        String deaths= jsonObject.getString("deaths");
                        String todaydeaths= jsonObject.getString("todayDeaths");
                        String recoverd= jsonObject.getString("recovered");
                        String active= jsonObject.getString("active");
                        String critical= jsonObject.getString("critical");
                        String casepermill=jsonObject.getString("casesPerOneMillion");
                        String deathpermill=jsonObject.getString("deathsPerOneMillion");
                        String testpermill=jsonObject.getString("testsPerOneMillion");
                        String totaltests=jsonObject.getString("tests");


                        JSONObject object=jsonObject.getJSONObject("countryInfo");

                        String Flagurl=object.getString("flag");

                        countrymodel=new countrymodel(Flagurl,CountryName,cases,todaycases,deaths,todaydeaths,recoverd,active,critical,casepermill,deathpermill,testpermill,totaltests);

                        countrymodelList.add(countrymodel);
                    }

                    custAdapter=new CustAdapter(Main2Activity.this,countrymodelList);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                    listView.setAdapter(custAdapter);




                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);

            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}
