package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Custadpater2 extends ArrayAdapter<statemodel> {

    private  Context context;
    private List<statemodel> statemodelList;
    private List<statemodel> statemodelfilterList;

    public Custadpater2(Context context,List<statemodel> statemodelList) {
        super(context,R.layout.listcustitem2,statemodelList);

        this.context=context;
        this.statemodelList=statemodelList;



    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcustitem2,null,true);

        TextView tvstatename=view.findViewById(R.id.tvStatename);

        tvstatename.setText(statemodelList.get(position).getState());
       // tvstatename.setText(statemodelList.get(position).getDistrict() +" Active Case : "+ statemodelList.get(position).getActive()+" Total Case : "+ statemodelList.get(position).getConfirmed());

        return view;
    }



}
