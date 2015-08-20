package com.amverhagen.crunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.amverhagen.options.Options;

public class SettingsActivity extends AppCompatActivity {
    private CheckBox subBox;
    private CheckBox multBox;
    private CheckBox divBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        subBox = (CheckBox) findViewById(R.id.checkbox_sub);
        multBox = (CheckBox) findViewById(R.id.checkbox_mult);
        divBox = (CheckBox) findViewById(R.id.checkbox_div);
        setBoxes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void setBoxes() {
        if (Options.getSubSetting()) subBox.setChecked(true);
        if (Options.getMultSetting()) multBox.setChecked(true);
        if (Options.getDivSetting()) divBox.setChecked(true);
    }

    public void subChecked(View view) {
        Options.toggleSub();
    }

    public void multChecked(View view) {
        Options.toggleMult();
    }

    public void divChecked(View view) {
        Options.toggleDiv();
    }

}
