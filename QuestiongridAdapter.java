package com.example.mocktest_app.Adapters;

import static com.example.mocktest_app.dbquery.ANSWERED;
import static com.example.mocktest_app.dbquery.NOT_VISITED;
import static com.example.mocktest_app.dbquery.REVIEW;
import static com.example.mocktest_app.dbquery.UNANSWERED;
import static com.example.mocktest_app.dbquery.gQuestionList;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.mocktest_app.Activities.Questions;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;

public class QuestiongridAdapter extends BaseAdapter {

    private int noofque;
    private Context context;

    public QuestiongridAdapter(Context context,int noofque) {
        this.noofque = noofque;
        this.context = context;
    }

    @Override
    public int getCount() {
        return noofque;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view ;
        if (convertView == null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questiongriditem,parent,false);

        }
        else {
            view = convertView;

        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof Questions)
                    ((Questions)context).goToQuestion(position);

            }
        });

        TextView questv = view.findViewById(R.id.ques_num);
        questv.setText(String.valueOf(position+1));

        Log.d("LOGGGGG",String.valueOf(gQuestionList.get(position).getStatus()));

        switch (dbquery.gQuestionList.get(position).getStatus())
        {
            case NOT_VISITED :
                questv.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.grey)));
                break;
            case ANSWERED :
                questv.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.green)));
                break;
            case UNANSWERED :
                questv.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.red)));
                break;
            case REVIEW :
                questv.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.pink)));
                break;
            default :
                break;

        }

        return view;
    }
}
