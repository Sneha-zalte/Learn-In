package com.example.mocktest_app.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Models.Questionmodel;
import com.example.mocktest_app.R;

import java.util.List;

public class AnswerAdaptor extends RecyclerView.Adapter<AnswerAdaptor.ViewHolder> {


    private final List<Questionmodel> quesList;

    public AnswerAdaptor(List<Questionmodel> quesList) {
        this.quesList = quesList;
    }

    @NonNull
    @Override
    public AnswerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answersitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdaptor.ViewHolder holder, int position) {

        String ques = quesList.get(position).getQuestion();
        String a = quesList.get(position).getA();
        String b = quesList.get(position).getB();
        String c = quesList.get(position).getC();
        String d = quesList.get(position).getD();
        int selected = quesList.get(position).getSelectedAns();
        int ans = quesList.get(position).getCorrectAns();

        holder.setData(position,ques,a,b,c,d,selected,ans);
    }

    @Override
    public int getItemCount() {
        return quesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView quesNo, question, optionA, optionB, optionC, optionD, result;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            quesNo = itemView.findViewById(R.id.quesNo);
            question = itemView.findViewById(R.id.question);
            optionA = itemView.findViewById(R.id.ansA);
            optionB = itemView.findViewById(R.id.ansB);
            optionC = itemView.findViewById(R.id.ansC);
            optionD = itemView.findViewById(R.id.ansD);
            result = itemView.findViewById(R.id.result);

        }

        @SuppressLint("SetTextI18n")
        private void setData(int pos, String ques, String a, String b, String c, String d, int selected, int correctAns)
        {
            quesNo.setText("Question No." + String.valueOf(pos+1));
            question.setText(ques);
            optionA.setText("A " + a);
            optionB.setText("B " + b);
            optionC.setText("C " + c);
            optionD.setText("D " + d);

            if (selected == -1)
            {
                result.setText("Un-Answered");
                result.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
                setOptionColor(selected, R.color.grey);
            }

            else
            {
                if (selected == correctAns)
                {
                    result.setText("Correct");
                    result.setTextColor(itemView.getContext().getResources().getColor(R.color.green));
                    setOptionColor(selected,R.color.green);
                }
                else
                {
                    result.setText("Wrong");
                    result.setTextColor(itemView.getContext().getResources().getColor(R.color.red));
                    setOptionColor(selected,R.color.red);

                }

            }

        }

        private void setOptionColor(int selected, int color)
        {

            if (selected == 1)
            {
                optionA.setTextColor(itemView.getContext().getResources().getColor(color));
            }
            else
            {
                optionA.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
            }

            if (selected == 2)
            {
                optionB.setTextColor(itemView.getContext().getResources().getColor(color));
            }
            else
            {
                optionB.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
            }

            if (selected == 3)
            {
                optionC.setTextColor(itemView.getContext().getResources().getColor(color));
            }
            else
            {
                optionC.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
            }

            if (selected == 4)
            {
                optionD.setTextColor(itemView.getContext().getResources().getColor(color));
            }
            else
            {
                optionD.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
            }


        }
    }
}
