package com.amverhagen.crunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreActivity extends AppCompatActivity {
    private int corrects;
    private int currentCorrectBox;
    private int secondsLeft;
    private int scoreFromCorrects;
    private int scoreFromTime;
    private int finalScore;
    private int scoreTimerLocation;
    private TextView[] correctBoxes;
    private TextView correctResultView;
    private TextView timeLeftView;
    private TextView timeResultView;
    private TextView finalScoreView;
    private Timer correctsTimer;
    private Timer timeLeftTimer;
    private TimerTask correctsTask;
    private TimerTask timeLeftTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        corrects = getIntent().getIntExtra("corrects", 0);
        secondsLeft = getIntent().getIntExtra("secondsLeft", 0);
        currentCorrectBox = 0;
        scoreFromCorrects = corrects * 20;
        scoreFromTime = secondsLeft;
        finalScore = scoreFromCorrects + scoreFromTime;
        scoreTimerLocation = 0;
        this.initCorrectBoxes();
        correctResultView = (TextView) this.findViewById(R.id.correctResultView);
        timeLeftView = (TextView) this.findViewById(R.id.timeLeftView);
        timeResultView = (TextView) this.findViewById(R.id.timeResultView);
        finalScoreView = (TextView) this.findViewById(R.id.finalScoreView);
        System.out.println("Final score is: " + finalScore);
        fillCorrectBoxes();
        fillScoreViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initCorrectBoxes() {
        correctBoxes = new TextView[10];
        correctBoxes[0] = (TextView) this.findViewById(R.id.correctView0);
        correctBoxes[1] = (TextView) this.findViewById(R.id.correctView1);
        correctBoxes[2] = (TextView) this.findViewById(R.id.correctView2);
        correctBoxes[3] = (TextView) this.findViewById(R.id.correctView3);
        correctBoxes[4] = (TextView) this.findViewById(R.id.correctView4);
        correctBoxes[5] = (TextView) this.findViewById(R.id.correctView5);
        correctBoxes[6] = (TextView) this.findViewById(R.id.correctView6);
        correctBoxes[7] = (TextView) this.findViewById(R.id.correctView7);
        correctBoxes[8] = (TextView) this.findViewById(R.id.correctView8);
        correctBoxes[9] = (TextView) this.findViewById(R.id.correctView9);
    }

    private void fillCorrectBoxes() {
        resetCorrectsTimer();
        resetCorrectsTask();
        correctsTimer.scheduleAtFixedRate(correctsTask, 1000, 50);
    }

    private void resetCorrectsTimer() {
        if (correctsTimer != null) correctsTimer.cancel();
        correctsTimer = new Timer();
    }

    private void resetCorrectsTask() {
        if (correctsTask != null) correctsTask.cancel();
        correctsTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Ran Corrects Task");
                        fillNextBox();
                    }
                });
            }
        };
    }

    private void fillNextBox() {
        if (currentCorrectBox < correctBoxes.length && currentCorrectBox < corrects) {
            correctBoxes[currentCorrectBox].setBackgroundColor(getResources().getColor(R.color.green));
            currentCorrectBox++;
        } else {
            cancelCorrectsTimerAndTask();
        }
    }

    private void fillScoreViews() {
        resetTimeLeftTimer();
        resetTimeLeftTask();
        timeLeftTimer.scheduleAtFixedRate(timeLeftTask, 500, 2);
    }

    private void resetTimeLeftTimer() {
        if (timeLeftTimer != null) timeLeftTimer.cancel();
        timeLeftTimer = new Timer();
    }

    private void resetTimeLeftTask() {
        if (timeLeftTask != null) timeLeftTask.cancel();
        timeLeftTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        incrementScoreViews();
                    }
                });
            }
        };
    }

    private void incrementScoreViews() {
        scoreTimerLocation++;
        boolean finished = true;
        if (scoreTimerLocation <= scoreFromCorrects) {
            finished = false;
            correctResultView.setText(scoreTimerLocation + "");
        }
        if (scoreTimerLocation <= scoreFromTime) {
            finished = false;
            timeResultView.setText(scoreTimerLocation + "");
        }

        if (scoreTimerLocation <= secondsLeft) {
            finished = false;
            timeLeftView.setText(scoreTimerLocation + "");
        }

        if (scoreTimerLocation <= finalScore) {
            finished = false;
            finalScoreView.setText(scoreTimerLocation + "");
        }

        if (finished) {
            cancelTimeLeftTimerAndTask();
        }
    }

    private void cancelCorrectsTimerAndTask() {
        if (correctsTimer != null) correctsTimer.cancel();
        if (correctsTask != null) correctsTask.cancel();
    }

    private void cancelTimeLeftTimerAndTask() {
        if (timeLeftTimer != null) timeLeftTimer.cancel();
        if (timeLeftTask != null) timeLeftTask.cancel();
    }

    private void endAllTimersAndTasks() {
        cancelCorrectsTimerAndTask();
        cancelTimeLeftTimerAndTask();
    }

    public void playAgain(View view) {
        this.finish();
        Intent intent = new Intent(this, CrunchActivity.class);
        startActivity(intent);
    }

    public void mainMenu(View view) {
        this.finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        endAllTimersAndTasks();
        this.finish();
    }
}
