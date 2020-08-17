package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class custadapter3 extends ArrayAdapter<statemodel>
{
    private  Context context;
    private List<statemodel> districtmodelList;

    public custadapter3(Context context,List<statemodel> districtmodelList) {
        super(context,R.layout.listcustitem2,districtmodelList);
        this.context=context;
        this.districtmodelList=districtmodelList;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcustitem2,null,true);

        TextView tvstatename=view.findViewById(R.id.tvStatename);

        tvstatename.setText(districtmodelList.get(position).getDistrict());
        // tvstatename.setText(statemodelList.get(position).getDistrict() +" Active Case : "+ statemodelList.get(position).getActive()+" Total Case : "+ statemodelList.get(position).getConfirmed());

        return view;
    }
}
