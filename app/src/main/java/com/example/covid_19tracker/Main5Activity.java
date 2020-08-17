package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Main5Activity extends AppCompatActivity {

    ListView listViewdistirct;
    private  int poistionstate;
    public String  statename;
    SimpleArcLoader simpleArcLoader;

    public  static List<statemodel> distrtictarrylist= new ArrayList<>();
    public statemodel statmoodel;
    custadapter3 Custadpater3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent intent=getIntent();
         poistionstate= intent.getIntExtra("pos2", 0);
         statename=intent.getStringExtra("State");

        listViewdistirct=findViewById(R.id.listofdistrict);
        simpleArcLoader=findViewById(R.id.loader12);

        getSupportActionBar().setTitle("Select District of "+ statename+"");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchdata();

        listViewdistirct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Districselect=distrtictarrylist.get(position).getDistrict().toString();

                startActivity(new Intent(getApplicationContext(),Main6Activity.class).putExtra("position",position).putExtra("districtname",Districselect));

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



                        JSONObject jsonObject=jsonArray.getJSONObject(poistionstate);


                        JSONArray jsonArray1=jsonObject.getJSONArray("districtData");
                    distrtictarrylist.clear();

                        for (int j=0;j<jsonArray1.length();j++)
                        {

                            JSONObject object = jsonArray1.getJSONObject(j);

                            String District= object.getString("district");
                            String Activecase= object.getString("active");
                            String Totalcase= object.getString("confirmed");
                            String Dead= object.getString("deceased");
                            String Recovered= object.getString("recovered");


                            statmoodel=new statemodel(District,Activecase,Totalcase,Dead,Recovered);

                            distrtictarrylist.add(statmoodel);




                        }




                    Custadpater3=new custadapter3(Main5Activity.this,distrtictarrylist);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                    listViewdistirct.setAdapter(Custadpater3);




                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main5Activity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);

            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}
