package com.example.mocktest_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.mocktest_app.Adapters.categoryadaptor;

/**
 * A simple {@link Fragment} subclass.
 */
public class categoryFragment extends Fragment {

    public categoryFragment() {
        // Required empty public constructor
    }

    private GridView catView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        catView = view.findViewById(R.id.cat_Grid);




        categoryadaptor categoryadaptor = new categoryadaptor(dbquery.gcatList);
        catView.setAdapter(categoryadaptor);
        return view;
    }
    /*private void loadcategories()
    {
        catList.clear();
        catList.add(new categorymodel("1","C",3));
        catList.add(new categorymodel("2","HTML",3));
        catList.add(new categorymodel("3","OFFICE",3));
        catList.add(new categorymodel("4","DBMS",3));
        catList.add(new categorymodel("5","MATHS",3));

    }*/
}