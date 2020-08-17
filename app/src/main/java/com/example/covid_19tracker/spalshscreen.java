package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class spalshscreen extends AppCompatActivity {

    TextView textView;
    private  int sleeptime=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalshscreen);
        textView=findViewById(R.id.textView);
        getSupportActionBar().hide();

        LOGOLanuch lg=new LOGOLanuch();
        lg.start();

    }
    private  class  LOGOLanuch extends  Thread
    {
        public  void  run()
        {

            try
            {
                sleep(1000 * sleeptime);

            }

            catch (InterruptedException e)
            {

                e.printStackTrace();
            }

            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            spalshscreen.this.finish();
        }
    }
}
