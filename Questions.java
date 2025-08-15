package com.example.mocktest_app.Activities;

import static com.example.mocktest_app.dbquery.ANSWERED;
import static com.example.mocktest_app.dbquery.NOT_VISITED;
import static com.example.mocktest_app.dbquery.REVIEW;
import static com.example.mocktest_app.dbquery.UNANSWERED;
import static com.example.mocktest_app.dbquery.gQuestionList;
import static com.example.mocktest_app.dbquery.gselectedtest_id;
import static com.example.mocktest_app.dbquery.gtestList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.mocktest_app.Adapters.Questionadapter;
import com.example.mocktest_app.Adapters.QuestiongridAdapter;
import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.ScoreActivity;
import com.example.mocktest_app.dbquery;

import java.util.concurrent.TimeUnit;

public class Questions extends AppCompatActivity {

    RecyclerView questionview;
    TextView QuesId,Qtimer,Qcatname;
    Button Qsubmit,Qmark,Qclear;
    ImageButton Qback,Qnext,Closebtn;
    ImageView Quelist,Quesave;
    private int Quesid;
    Questionadapter questionadapter;
    private DrawerLayout drawer;
    GridView QuestionlistGV;
    ImageView markimg;
    QuestiongridAdapter questiongridAdapter;
    private CountDownTimer timer;
    long timeleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionlist);

        init();

        questionadapter = new Questionadapter(gQuestionList);
        questionview.setAdapter(questionadapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionview.setLayoutManager(layoutManager);

        questiongridAdapter = new QuestiongridAdapter(this,gQuestionList.size());
        QuestionlistGV.setAdapter(questiongridAdapter);

        setSnapHelper();
        setClickListener();
        setTimer();
    }

    @SuppressLint("SetTextI18n")
    public void init()
    {
        questionview = findViewById(R.id.questionview);
        QuesId = findViewById(R.id.queid);
        Qtimer = findViewById(R.id.quetime);
        Qcatname = findViewById(R.id.quecat);
        Qsubmit = findViewById(R.id.quesubmit);
        Qmark = findViewById(R.id.quemark);
        Qclear = findViewById(R.id.queclear);
        Qback = findViewById(R.id.queback);
        Qnext = findViewById(R.id.quenext);
        Quelist = findViewById(R.id.quelistmenu);
        Quesave = findViewById(R.id.quesave);
        drawer = findViewById(R.id.drawer_layout);
        Closebtn = findViewById(R.id.closebtn);
        QuestionlistGV = findViewById(R.id.questionlistgridview);
        markimg = findViewById(R.id.markimg);


        Quesid = 0;
        QuesId.setText("1/" + String.valueOf(gQuestionList.size()));
        Qcatname.setText(dbquery.gcatList.get(dbquery.gselectedcat_id).getName());

        gQuestionList.get(0).setStatus(UNANSWERED);

        if (gQuestionList.get(0).isBookmarked())
        {
            Quesave.setImageResource(R.drawable.ic_action_booked);
        }
        else
        {
            Quesave.setImageResource(R.drawable.ic_action_unbook);
        }
    }

    private void setSnapHelper()
    {
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(questionview);

        questionview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                View view = snapHelper.findSnapView(recyclerView.getLayoutManager());
                Quesid = recyclerView.getLayoutManager().getPosition(view);

                if (gQuestionList.get(Quesid).getStatus() == NOT_VISITED)
                    gQuestionList.get(Quesid).setStatus(UNANSWERED);

                if (gQuestionList.get(Quesid).getStatus() == REVIEW)
                {
                    markimg.setVisibility(View.VISIBLE);
                }
                else
                {
                    markimg.setVisibility(View.GONE);
                }

                QuesId.setText(String.valueOf(Quesid + 1) + "/" + String.valueOf(gQuestionList.size()));

                if (gQuestionList.get(Quesid).isBookmarked())
                {
                    Quesave.setImageResource(R.drawable.ic_action_booked);
                }
                else
                {
                    Quesave.setImageResource(R.drawable.ic_action_unbook);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setClickListener()
    {
        Quesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addtoBookmark();
            }
        });

        Qback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Quesid >0)
                {
                    questionview.smoothScrollToPosition(Quesid-1);
                }

            }
        });

        Qnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Quesid < gQuestionList.size()-1)
                {
                    questionview.smoothScrollToPosition(Quesid+1);
                }

            }
        });

        Qclear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                gQuestionList.get(Quesid).setSelectedAns(-1);
                gQuestionList.get(Quesid).setStatus(UNANSWERED);
                markimg.setVisibility(View.GONE);
                questionadapter.notifyDataSetChanged();

            }
        });

        Quelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen(GravityCompat.END))
                {
                    questiongridAdapter.notifyDataSetChanged();
                    drawer.openDrawer(GravityCompat.END);
                }

            }
        });

        Closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END))
                {
                    drawer.closeDrawer(GravityCompat.END);
                }
            }
        });

        Qmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markimg.getVisibility() != View.VISIBLE)
                {
                    markimg.setVisibility(View.VISIBLE);
                    gQuestionList.get(Quesid).setStatus(REVIEW);
                }
                else
                {
                    markimg.setVisibility(View.GONE);

                    if (gQuestionList.get(Quesid).getSelectedAns() != -1)
                    {
                        gQuestionList.get(Quesid).setStatus(ANSWERED);
                    }
                    else
                    {
                        gQuestionList.get(Quesid).setStatus(UNANSWERED);
                    }
                }
            }
        });

        Qsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTest();
            }
        });
    }

    private void submitTest()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Questions.this);
        builder.setCancelable(true);
        View v = getLayoutInflater().inflate(R.layout.salert_dialog_layout,null);

        Button cancel = v.findViewById(R.id.cancelbtn);
        Button conform = v.findViewById(R.id.conformbtn);

        builder.setView(v);

        AlertDialog alertDialog = builder.create();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.cancel();
                alertDialog.dismiss();

                dbquery.loadMyScores(new MyCompleteListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(Questions.this, "Scores Added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(Questions.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(Questions.this, ScoreActivity.class);
                long totaltime = (long) gtestList.get(gselectedtest_id).getTime()*60*1000;
                intent.putExtra("TIME_TAKEN",totaltime-timeleft);
                startActivity(intent);
                Questions.this.finish();


            }
        });

        alertDialog.show();
    }

    public void goToQuestion(int position)
    {
        questionview.smoothScrollToPosition(position);

        if (drawer.isDrawerOpen(GravityCompat.END))
        {
            drawer.closeDrawer(GravityCompat.END);
        }
    }

    private void setTimer()
    {
        long totaltime = (long) dbquery.gtestList.get(dbquery.gselectedtest_id).getTime()*60*1000;
        timer = new CountDownTimer(totaltime+1000,1000) {
            @Override
            public void onTick(long remains) {

                timeleft = remains;

                @SuppressLint("DefaultLocale")
                String time = String.format("%02d : %02d min",
                        TimeUnit.MILLISECONDS.toMinutes(remains),
                        TimeUnit.MILLISECONDS.toSeconds(remains)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remains)));

                Qtimer.setText(time);

            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(Questions.this, ScoreActivity.class);
                long totaltime = (long) gtestList.get(gselectedtest_id).getTime()*60*1000;
                intent.putExtra("TIME_TAKEN",totaltime-timeleft);
                startActivity(intent);
                Questions.this.finish();

            }
        };
        timer.start();
    }

    private void addtoBookmark()
    {
        if (gQuestionList.get(Quesid).isBookmarked())
        {
            gQuestionList.get(Quesid).setBookmarked(false);
            Quesave.setImageResource(R.drawable.ic_action_unbook);

        }
        else
        {
            gQuestionList.get(Quesid).setBookmarked(true);
            Quesave.setImageResource(R.drawable.ic_action_booked);
        }
    }
}