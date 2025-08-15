package com.example.mocktest_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Models.Rankmodel;
import com.example.mocktest_app.R;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter <RankAdapter.ViewHolder>{

    private List<Rankmodel> userList;

    public RankAdapter(List<Rankmodel> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rantitemlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, int position) {

        String name = userList.get(position).getName();
        int score = userList.get(position).getScore();
        int rank = userList.get(position).getRank();

        holder.setData(name,score,rank);

    }

    @Override
    public int getItemCount() {

        if (userList.size() > 10)
        {
            return 10;
        }
        else
        {
            return userList.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV,rankTV,scoreTV,imgTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.name);
            rankTV = itemView.findViewById(R.id.rank);
            scoreTV = itemView.findViewById(R.id.Score);
            imgTV = itemView.findViewById(R.id.imgtxt);
        }

        private void setData(String name, int score, int rank)
        {
            nameTV.setText(name);
            scoreTV.setText("Score:" +score);
            rankTV.setText("Rank-"+rank);
            imgTV.setText(name.toUpperCase().substring(0,1));
        }
    }
}