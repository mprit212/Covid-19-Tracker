package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    ListView listViewstate;
    EditText edtSearch4;
    SimpleArcLoader  simpleArcLoader;
    public  String StateName;

    public  static List<statemodel> statemodelArrayList= new ArrayList<>();
    public statemodel statmoodel;
    public  static List<statemodel> statemodelArrayList2=new ArrayList<>();

    Custadpater2 Custadpater2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        listViewstate=findViewById(R.id.listofstate);
        simpleArcLoader=findViewById(R.id.loader33);

        getSupportActionBar().setTitle("Select State Of India");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchdata();




        listViewstate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Statenameclick=statemodelArrayList2.get(position).getState().toString();
                startActivity(new Intent(getApplicationContext(),Main5Activity.class).putExtra("pos2",position).putExtra("State",Statenameclick));

            }
        });
    }

    private void fetchdata()
    {

        String url="https://api.covid19india.org/v2/state_district_wise.json";

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

                         StateName= jsonObject.getString("state");

                     //  JSONArray jsonArray1=jsonObject.getJSONArray("districtData");


                       statmoodel=new statemodel(StateName);
                       statemodelArrayList2.add(statmoodel);




                    }

                    Custadpater2=new Custadpater2(Main4Activity.this,statemodelArrayList2);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                    listViewstate.setAdapter(Custadpater2);




                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main4Activity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);

            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}
