package com.amverhagen.crunch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.amverhagen.problem.Equation;
import com.amverhagen.problem.EquationWrapper;

import java.util.ArrayList;

public class CrunchActivity extends AppCompatActivity {
    private ArrayList<Equation> equations;
    private EquationWrapper wrapper;
    private TextView equationPanel;
    private TextView topLeftPanel;
    private TextView topRightPanel;
    private TextView botLeftPanel;
    private TextView botRightPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crunch);
        wrapper = (EquationWrapper) getIntent().getSerializableExtra("equations");
        equations = wrapper.getEquations();
        System.out.println("Hello, World" + " " + equations.size());
        Equation e = equations.remove(0);
        System.out.println(e.toString());
        equationPanel = (TextView) findViewById(R.id.equationPanel);
        topLeftPanel = (TextView) findViewById(R.id.topLeftPanel);
        topRightPanel = (TextView) findViewById(R.id.topRightPanel);
        botLeftPanel = (TextView) findViewById(R.id.botLeftPanel);
        botRightPanel = (TextView) findViewById(R.id.botRightPanel);
        this.loadTextViewsWithEquation(e);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadTextViewsWithEquation(Equation e) {
        equationPanel.setText(e.getEquationSyntax());
        topLeftPanel.setText(e.getCorrectAnswer());
        topRightPanel.setText(e.getIncorrectAnswer(0));
        botLeftPanel.setText(e.getIncorrectAnswer(1));
        botRightPanel.setText(e.getIncorrectAnswer(2));
    }
}
