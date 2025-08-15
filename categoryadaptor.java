package com.example.mocktest_app.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mocktest_app.Models.categorymodel;
import com.example.mocktest_app.R;
import com.example.mocktest_app.Activities.Test;
import com.example.mocktest_app.dbquery;

import java.util.List;

public class categoryadaptor extends BaseAdapter {

    public List<categorymodel> catList;
    private android.view.ViewGroup ViewGroup;

    public categoryadaptor(List<categorymodel>catList)
    {
        this.catList=catList;
    }
    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View myview;
        if (convertView == null)
        {
            myview = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryitem,parent,false);
        }
        else {
            myview = convertView;
        }

        myview.setOnClickListener(v -> {
            dbquery.gselectedcat_id = position;
            Intent intent = new Intent(v.getContext(), Test.class);
            v.getContext().startActivity(intent);
        });

        TextView catName = myview.findViewById(R.id.catname);
        TextView noofTest = myview.findViewById(R.id.nooftest);

        catName.setText(catList.get(position).getName());
        noofTest.setText(String.valueOf(catList.get(position).getNoOfTests()) + "Tests");


        return myview;
    }
}
