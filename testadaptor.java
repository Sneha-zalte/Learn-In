package com.example.mocktest_app.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Models.Testmodel;
import com.example.mocktest_app.R;
import com.example.mocktest_app.StartTest;
import com.example.mocktest_app.dbquery;

import java.util.List;

public class testadaptor extends RecyclerView.Adapter<testadaptor.ViewHolder>
{
    private final List<Testmodel> testList;
    public testadaptor(List<Testmodel> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public testadaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull testadaptor.ViewHolder holder, int position)
    {
        int progress = testList.get(position).getTopScore();
        holder.setdata(position,progress);

    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testno;
        private TextView topscore;
        private ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testno = itemView.findViewById(R.id.testno);
            topscore = itemView.findViewById(R.id.scoretext);
            progressBar = itemView.findViewById(R.id.testprogress);
        }
        private void setdata(int pos,int progress)
        {
            testno.setText("Test No:" + String.valueOf(pos+1));
            topscore.setText(String.valueOf(progress)+ "%");
            progressBar.setProgress(progress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbquery.gselectedtest_id = pos;

                    Intent intent = new Intent(itemView.getContext(), StartTest.class);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
