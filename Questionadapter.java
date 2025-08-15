package com.example.mocktest_app.Adapters;

import static com.example.mocktest_app.dbquery.ANSWERED;
import static com.example.mocktest_app.dbquery.REVIEW;
import static com.example.mocktest_app.dbquery.UNANSWERED;
import static com.example.mocktest_app.dbquery.gQuestionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Models.Questionmodel;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;

import java.util.List;

public class Questionadapter extends RecyclerView.Adapter<Questionadapter.ViewHolder> {

    private final List<Questionmodel>QuestionList;

    public Questionadapter(List<Questionmodel>questionList) {
        QuestionList = questionList;
    }

    @NonNull
    @Override
    public Questionadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Questionadapter.ViewHolder holder, int position) {
        holder.setdata(position);
    }

    @Override
    public int getItemCount() {
        return QuestionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        Button optionA,optionB,optionC,optionD,prevSelectbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            optionA = itemView.findViewById(R.id.OptionA);
            optionB = itemView.findViewById(R.id.OptionB);
            optionC = itemView.findViewById(R.id.OptionC);
            optionD = itemView.findViewById(R.id.OptionD);
            prevSelectbtn = null;
        }

        private void setdata(final int pos)
        {
            question.setText(QuestionList.get(pos).getQuestion());
            optionA.setText(QuestionList.get(pos).getA());
            optionB.setText(QuestionList.get(pos).getB());
            optionC.setText(QuestionList.get(pos).getC());
            optionD.setText(QuestionList.get(pos).getD());

            setOptions(optionA,1, pos);
            setOptions(optionB,2, pos);
            setOptions(optionC,3, pos);
            setOptions(optionD,4, pos);

            optionA.setOnClickListener(v -> selectOption(optionA,1,pos));

            optionB.setOnClickListener(v -> selectOption(optionB,2,pos));

            optionC.setOnClickListener(v -> selectOption(optionC, 3, pos));

            optionD.setOnClickListener(v -> selectOption(optionD,4,pos));

        }
        private void selectOption(Button btn, int Optionno, int Quesid)
        {
            if (prevSelectbtn == null)
            {
                btn.setBackgroundResource(R.drawable.selectedbtn);
                dbquery.gQuestionList.get(Quesid).setSelectedAns(Optionno);

                changeStatus(Quesid,ANSWERED);

                prevSelectbtn = btn;
            }
            else
            {
                if (prevSelectbtn.getId() == btn.getId())
                {
                    btn.setBackgroundResource(R.drawable.unselectedbtn);
                    dbquery.gQuestionList.get(Quesid).setSelectedAns(-1);

                    changeStatus(Quesid,UNANSWERED);

                    prevSelectbtn = null;
                }
                else
                {
                    prevSelectbtn.setBackgroundResource(R.drawable.unselectedbtn);
                    btn.setBackgroundResource(R.drawable.selectedbtn);

                    dbquery.gQuestionList.get(Quesid).setSelectedAns(Optionno);

                    changeStatus(Quesid,ANSWERED);

                    prevSelectbtn=btn;
                }

            }

        }

        private void changeStatus(int id , int status)
        {
            if (gQuestionList.get(id).getStatus() != REVIEW)
            {
                gQuestionList.get(id).setStatus(status);
            }
        }

        private void setOptions(Button btn, int Optionno, int Quesid)
        {
            if (dbquery.gQuestionList.get(Quesid).getSelectedAns()== Optionno)
            {
                btn.setBackgroundResource(R.drawable.selectedbtn);
            }
            else
            {
                btn.setBackgroundResource(R.drawable.unselectedbtn);
            }

        }
    }
}
