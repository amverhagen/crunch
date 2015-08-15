package com.amverhagen.crunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private int leftValue;
    private int rightValue;
    private TextView leftTextView;
    private TextView rightTextView;
    private TextView warningTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        leftTextView = (TextView) findViewById(R.id.leftTextView);
        rightTextView = (TextView) findViewById(R.id.rightTextView);
        warningTextView = (TextView) findViewById(R.id.warningTextView);
        leftValue = 1;
        rightValue = 50;
        setRangeValues();
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

    private void setRangeValues() {
        leftTextView.setText(Integer.toString(leftValue));
        rightTextView.setText(Integer.toString(rightValue));
    }

    public void startCrunch(View view) {
        if (testRange()) {
            Intent intent = new Intent(this, CrunchActivity.class);
            startActivity(intent);
        }
    }

    public void incrementLeft(View view) {
        if (leftValue < 99) {
            leftValue++;
            setRangeValues();
        }
    }

    public void decrementLeft(View view) {
        if (leftValue > 1) {
            leftValue--;
            setRangeValues();
        }
    }

    public void incrementRight(View view) {
        if (rightValue < 99) {
            rightValue++;
            setRangeValues();
        }
    }

    public void decrementRight(View view) {
        if (rightValue > 1) {
            rightValue--;
            setRangeValues();
        }
    }

    private boolean testRange() {
        if (Math.abs(leftValue - rightValue) < 10) {
            warningTextView.setText("Difference between values must be greater than 10.");
            return false;
        } else {
            warningTextView.setText("");
            return true;
        }
    }
}
