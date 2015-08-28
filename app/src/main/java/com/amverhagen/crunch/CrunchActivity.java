package com.amverhagen.crunch;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amverhagen.problem.Equation;
import com.amverhagen.problem.EquationWrapper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CrunchActivity extends AppCompatActivity {
    private MediaPlayer correct;
    private MediaPlayer wrong;
    private boolean active;
    private String equationText;
    private int currentTime;
    private int currentTimeBox;
    private Timer secondsTimer;
    private TimerTask secondPassedTask;
    private TimerTask displayAnswerTask;
    private ArrayList<Equation> equations;
    private EquationWrapper wrapper;
    private TextView equationPanel;
    private Panel[] panels;
    private Panel selectedPanel;
    private TextView[] checkBoxes;
    private TextView[] timeBoxes;
    private int currentScoreBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crunch);
        wrapper = (EquationWrapper) getIntent().getSerializableExtra("equations");
        equations = wrapper.getEquations();
        active = false;
        this.setSounds();
        this.initTimeBoxes();
        this.initScoreBoxes();
        this.initPanels();
        this.loadNextEquation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crunch, menu);
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

    private void setSounds() {
        wrong = MediaPlayer.create(this, R.raw.wrong);
        correct = MediaPlayer.create(this, R.raw.correct);
    }

    private void setTimerTasks() {
        secondPassedTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        decrementSecond();
                    }
                });
            }
        };
        displayAnswerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadNextEquation();
                    }
                });
            }
        };
    }


    private void decrementSecond() {
        currentTime--;
        grayCurrentBox();
        if (currentTime <= 0) {
            if (selectedPanel == null) setAnswer(false);
            else setAnswer(selectedPanel.getCorrectness());
        }

    }

    private void grayCurrentBox() {
        if (currentTimeBox > timeBoxes.length - 1) currentTimeBox = timeBoxes.length - 1;
        timeBoxes[currentTimeBox].setBackgroundColor(Color.GRAY);
        currentTimeBox++;
    }

    private void initTimeBoxes() {
        currentTimeBox = 0;
        timeBoxes = new TextView[5];
        timeBoxes[0] = (TextView) findViewById(R.id.time5);
        timeBoxes[1] = (TextView) findViewById(R.id.time4);
        timeBoxes[2] = (TextView) findViewById(R.id.time3);
        timeBoxes[3] = (TextView) findViewById(R.id.time2);
        timeBoxes[4] = (TextView) findViewById(R.id.time1);
    }


    private void initScoreBoxes() {
        currentScoreBox = 0;
        checkBoxes = new TextView[10];
        checkBoxes[0] = (TextView) findViewById(R.id.cb0);
        checkBoxes[1] = (TextView) findViewById(R.id.cb1);
        checkBoxes[2] = (TextView) findViewById(R.id.cb2);
        checkBoxes[3] = (TextView) findViewById(R.id.cb3);
        checkBoxes[4] = (TextView) findViewById(R.id.cb4);
        checkBoxes[5] = (TextView) findViewById(R.id.cb5);
        checkBoxes[6] = (TextView) findViewById(R.id.cb6);
        checkBoxes[7] = (TextView) findViewById(R.id.cb7);
        checkBoxes[8] = (TextView) findViewById(R.id.cb8);
        checkBoxes[9] = (TextView) findViewById(R.id.cb9);
    }

    private void initPanels() {
        equationPanel = (TextView) findViewById(R.id.equationPanel);
        panels = new Panel[4];
        panels[0] = new Panel((TextView) findViewById(R.id.topLeftPanel));
        panels[1] = new Panel((TextView) findViewById(R.id.topRightPanel));
        panels[2] = new Panel((TextView) findViewById(R.id.botLeftPanel));
        panels[3] = new Panel((TextView) findViewById(R.id.botRightPanel));
    }

    private void loadNextEquation() {
        if (equations.size() > 0) {
            currentTime = 5;
            currentTimeBox = 0;
            resetSelectedPanel();
            colorTimeBoxes();
            loadTextViewsWithEquation(equations.remove(0));
            resetTimer();
            active = true;
        } else {
            endTimer();
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void colorTimeBoxes() {
        timeBoxes[0].setBackgroundColor(Color.GREEN);
        timeBoxes[1].setBackgroundColor(Color.GREEN);
        timeBoxes[2].setBackgroundColor(Color.YELLOW);
        timeBoxes[3].setBackgroundColor(Color.YELLOW);
        timeBoxes[4].setBackgroundColor(Color.RED);
    }

    private void resetTimer() {
        endTimer();
        setTimerTasks();
        secondsTimer = new Timer();
        secondsTimer.scheduleAtFixedRate(secondPassedTask, 1000, 1000);
    }

    private void endTimer() {
        if (secondsTimer != null) {
            secondsTimer.cancel();
        }
        if (secondPassedTask != null) {
            secondPassedTask.cancel();
        }
    }

    private void loadTextViewsWithEquation(Equation e) {
        equationText = e.getEquationSyntax() + " = ";
        equationPanel.setBackgroundColor(Color.WHITE);
        equationPanel.setText(equationText);

        int correct = (int) Math.floor(Math.random() * 4);
        for (int i = 0; i < panels.length; i++) {
            if (i != correct) {
                panels[i].setText(e.getIncorrectAnswer());
                panels[i].setFalse();
            } else {
                panels[i].setText(e.getCorrectAnswer());
                panels[i].setCorrect();
            }
        }
    }

    public void panelTouched(View view) {
        if (active) {
            for (int i = 0; i < panels.length; i++) {
                if (panels[i].getTextView() == view) {
                    if (panels[i] == selectedPanel) {
                        setAnswer(panels[i].getCorrectness());
                    } else {
                        setSelectedPanel(panels[i]);
                    }
                }
            }
        }
    }

    public void setSelectedPanel(Panel selection) {
        if (selectedPanel != null)
            selectedPanel.getTextView().setBackgroundResource(R.drawable.border);
        selectedPanel = selection;
        equationPanel.setText(equationText + selectedPanel.getTextView().getText());
        selectedPanel.getTextView().setBackgroundResource(R.drawable.border_selected);
    }

    private void resetSelectedPanel() {
        selectedPanel = null;
        for (int i = 0; i < panels.length; i++) {
            panels[i].getTextView().setBackgroundResource(R.drawable.border);
        }
    }

    private void setAnswer(boolean correctness) {
        markBox(correctness);
    }

    private void markBox(Boolean correctness) {
        if (currentScoreBox > checkBoxes.length - 1) currentScoreBox = checkBoxes.length - 1;
        if (correctness) {
            checkBoxes[currentScoreBox].setBackgroundColor(Color.GREEN);
        } else {
            checkBoxes[currentScoreBox].setBackgroundColor(Color.RED);
        }
        highlightAnswer(correctness);
        currentScoreBox++;
    }

    private void highlightAnswer(boolean correctness) {
        endTimer();
        active = false;
        if (correctness) {
            correct.start();
            equationPanel.setBackgroundColor(Color.GREEN);
        } else {
            wrong.start();
            equationPanel.setBackgroundColor(Color.RED);
        }
        secondsTimer = new Timer();
        secondsTimer.scheduleAtFixedRate(displayAnswerTask, 1000, 1000);
    }

    @Override
    public void onStop() {
        super.onStop();
        endTimer();
        finish();
    }
}
