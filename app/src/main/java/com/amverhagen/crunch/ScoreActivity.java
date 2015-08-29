package com.amverhagen.crunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreActivity extends AppCompatActivity {
    private int corrects;
    private int secondsLeft;
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
        this.initCorrectBoxes();
        correctResultView = (TextView) this.findViewById(R.id.correctResultView);
        timeLeftView = (TextView) this.findViewById(R.id.timeLeftView);
        timeResultView = (TextView) this.findViewById(R.id.timeResultView);
        finalScoreView = (TextView) this.findViewById(R.id.finalScoreView);
        fillCorrectBoxes();
        fillTimeLeftView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
        setCorrectsTask();
        correctsTimer.scheduleAtFixedRate(correctsTask, 0, 500);
    }

    private void resetCorrectsTimer() {
        if (correctsTimer != null) correctsTimer.cancel();
        correctsTimer = new Timer();
    }

    private void setCorrectsTask() {
        if (correctsTask != null) correctsTask.cancel();
        correctsTask = new TimerTask() {
            @Override
            public void run() {
                this.cancel();
            }
        };
    }

    private void fillTimeLeftView() {
        resetTimeLeftTimer();
        setTimeLeftTask();
        timeLeftTimer.scheduleAtFixedRate(timeLeftTask, 0, 500);
    }

    private void resetTimeLeftTimer() {
        if (timeLeftTimer != null) timeLeftTimer.cancel();
        timeLeftTimer = new Timer();
    }

    private void setTimeLeftTask() {
        if (timeLeftTask != null) timeLeftTask.cancel();
        timeLeftTask = new TimerTask() {
            @Override
            public void run() {
                this.cancel();
            }
        };
    }
}
