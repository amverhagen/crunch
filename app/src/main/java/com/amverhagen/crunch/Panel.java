package com.amverhagen.crunch;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Andrew on 8/18/2015.
 */
public class Panel {
    private boolean correct;
    private TextView panel;

    public Panel(TextView panel) {
        this.panel = panel;
        this.correct = false;
    }

    public void setText(String text) {
        panel.setText(text);
    }

    public void setCorrect() {
        correct = true;
    }

    public void setFalse() {
        correct = false;
    }

    public boolean getCorrectness() {
        return correct;
    }

    public void touchTopLeft(View view) {
        System.out.println("touched topleft from panel");
    }
}
