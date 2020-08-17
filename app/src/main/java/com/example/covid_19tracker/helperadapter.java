package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class helperadapter extends ArrayAdapter<helpmodel> {
    private  Context context;
    private List<helpmodel> helpmodelList;
    public helperadapter(@NonNull Context context,List<helpmodel> helpmodelList) {
        super(context,R.layout.listitem3,helpmodelList);
        this.context=context;
        this.helpmodelList=helpmodelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem3,null,true);

        TextView tvname=view.findViewById(R.id.TVSTATENAME);
        TextView tvcase=view.findViewById(R.id.TVTOTALCASE);
        TextView tvdead=view.findViewById(R.id.TVTOTALDEAD);
        TextView tvrecover=view.findViewById(R.id.TVRECOVER);

        tvname.setText(helpmodelList.get(position).getState());
        tvcase.setText(helpmodelList.get(position).getTotalcase());
        tvdead.setText(helpmodelList.get(position).getDeaths());
        tvrecover.setText(helpmodelList.get(position).getRecover());




        return view;
    }
}
