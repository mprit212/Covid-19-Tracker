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

public class CustAdapter extends ArrayAdapter<countrymodel>
{
    private  Context context;
    private List<countrymodel> countrymodelList;
    private List<countrymodel> countrymodelfilterList;

    public CustAdapter(Context context,List<countrymodel> countrymodelList) {
        super(context,R.layout.listcustitem,countrymodelList);

        this.context=context;
        this.countrymodelList=countrymodelList;
        this.countrymodelfilterList=countrymodelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcustitem,null,true);

        TextView tvcountryname=view.findViewById(R.id.tvCountryname);
        ImageView imageView=view.findViewById(R.id.imageflag);

        tvcountryname.setText(countrymodelfilterList.get(position).getCountry());

        Glide.with(context).load(countrymodelfilterList.get(position).getFlag()).into(imageView);


        return view;
    }

    @Override
    public int getCount() {
        return countrymodelfilterList.size();
    }

    @Nullable
    @Override
    public countrymodel getItem(int position) {
        return countrymodelfilterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults filterResults=new FilterResults();
                if (constraint==null || constraint.length()==0)
                {
                    filterResults.count=countrymodelList.size();
                    filterResults.values=countrymodelList;
                }
                else
                {

                    List<countrymodel> ressultsmodel=new ArrayList<>();
                    String searchstr=constraint.toString().toLowerCase();

                    for (countrymodel itemesmodel:countrymodelList)
                    {
                        if (itemesmodel.getCountry().toLowerCase().contains(searchstr))
                        {
                            ressultsmodel.add(itemesmodel);
                        }
                        filterResults.count=ressultsmodel.size();
                        filterResults.values=ressultsmodel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {



                countrymodelfilterList=(List<countrymodel>) results.values;
                Main2Activity.countrymodelList=(List<countrymodel>) results.values;
                notifyDataSetChanged();


            }
        };
        return filter;
    }

}
