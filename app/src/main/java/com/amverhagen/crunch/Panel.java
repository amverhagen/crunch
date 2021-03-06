package com.amverhagen.crunch;

import android.widget.TextView;


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

    public TextView getTextView() {
        return this.panel;
    }
}
