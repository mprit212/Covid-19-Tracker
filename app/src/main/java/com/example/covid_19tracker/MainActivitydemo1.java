package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.Edits;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Iterator;
import java.util.List;

public class MainActivitydemo1 extends AppCompatActivity {

    ListView listView;
    TextView tvlast;
    SimpleArcLoader simpleArcLoader;

    public static List<helpmodel> helpmodelList=new ArrayList<helpmodel>();
    helpmodel helpmodel;
    helperadapter helperadapter;
    String lastupdateitme;
    public String URL2="https://www.mygov.in/covid-19";
    public String URL3="https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitydemo1);
        tvlast=findViewById(R.id.tvlastupdate);
        listView=findViewById(R.id.ListView77);
        getSupportActionBar().setTitle("States Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        View view=getLayoutInflater().inflate(R.layout.headerformate,null);
        listView.addHeaderView(view);

        fetchdata();
    }

    private void fetchdata()
    {

        String url="https://api.covid19india.org/data.json";




        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {
                  JSONObject object1=new JSONObject(response);
                    //Iterator<String> iterator=object1.keys();

                    //Iterator<String> iterator =object1.keys();

                    JSONArray keys=object1.names();


                    String ky=keys.getString(1);

                    JSONArray jsonArray1=object1.getJSONArray(ky);


                            for (int j=0;j<jsonArray1.length();j++) {

                                JSONObject jsonObject = jsonArray1.getJSONObject(j);

                                JSONObject jsonObject1 = jsonArray1.getJSONObject(0);

                                lastupdateitme = jsonObject1.getString("lastupdatedtime");

                                String NAME = jsonObject.getString("state");
                                String CASE = jsonObject.getString("confirmed");
                                String DEAD = jsonObject.getString("deaths");
                                String RECOVER = jsonObject.getString("recovered");
                                String ACTIVE = jsonObject.getString("active");



                                helpmodel=new helpmodel(NAME,CASE,DEAD,RECOVER,ACTIVE);
                                helpmodelList.add(helpmodel);


                        }






                    tvlast.setText("Last Update : "+lastupdateitme);

                    helperadapter=new helperadapter(MainActivitydemo1.this,helpmodelList);


                    listView.setAdapter(helperadapter);




                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivitydemo1.this,error.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void goup(View view) {
        startActivity(new Intent(MainActivitydemo1.this,Main4Activity.class).putExtra("updatetime",lastupdateitme));
        SharedPreferences preferences=getSharedPreferences("Updatetime",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("updatetime",lastupdateitme);
        editor.commit();
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
