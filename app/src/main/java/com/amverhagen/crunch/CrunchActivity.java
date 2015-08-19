package com.amverhagen.crunch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amverhagen.problem.Equation;
import com.amverhagen.problem.EquationWrapper;

import java.util.ArrayList;

public class CrunchActivity extends AppCompatActivity {
    private ArrayList<Equation> equations;
    private EquationWrapper wrapper;
    private TextView equationPanel;
    private Panel[] panels;
    private TextView[] checkBoxes;
    private int currentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crunch);
        wrapper = (EquationWrapper) getIntent().getSerializableExtra("equations");
        equations = wrapper.getEquations();
        equationPanel = (TextView) findViewById(R.id.equationPanel);
        this.initBoxes();
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

    private void initBoxes() {
        currentBox = 0;
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
        panels = new Panel[4];
        panels[0] = new Panel((TextView) findViewById(R.id.topLeftPanel));
        panels[1] = new Panel((TextView) findViewById(R.id.topRightPanel));
        panels[2] = new Panel((TextView) findViewById(R.id.botLeftPanel));
        panels[3] = new Panel((TextView) findViewById(R.id.botRightPanel));
    }

    private void loadNextEquation() {
        if (equations.size() > 0) {
            loadTextViewsWithEquation(equations.remove(0));
        } else {
            finish();
        }
    }

    private void loadTextViewsWithEquation(Equation e) {
        equationPanel.setText(e.getEquationSyntax());

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
        for (int i = 0; i < panels.length; i++) {
            if (panels[i].getTextView() == view) {
                checkPanel(panels[i]);
            }
        }
    }

    private void checkPanel(Panel p) {
        markBox(p.getCorrectness());
        loadNextEquation();
    }

    private void markBox(Boolean correctness) {
        if (currentBox > checkBoxes.length - 1) currentBox = checkBoxes.length - 1;
        if (correctness) {
            checkBoxes[currentBox].setBackgroundColor(Color.GREEN);
        } else {
            checkBoxes[currentBox].setBackgroundColor(Color.RED);
        }
        currentBox++;
    }
}
