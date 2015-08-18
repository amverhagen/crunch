package com.amverhagen.crunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amverhagen.options.Options;
import com.amverhagen.problem.Equation;
import com.amverhagen.problem.EquationWrapper;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    Options options;
    ArrayList<Equation> problemList;
    EquationWrapper wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        options = new Options();
        problemList = new ArrayList<Equation>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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


    public void startCrunch(View view) {
        problemList.clear();
        for (int i = 0; i < 10; i++) {
            Equation temp = new Equation(this.options);
            problemList.add(temp);
        }
        wrapper = new EquationWrapper(problemList);
        Intent intent = new Intent(this, CrunchActivity.class);
        intent.putExtra("equations", wrapper);
        startActivity(intent);
    }

    public void options(View view) {

    }

}
