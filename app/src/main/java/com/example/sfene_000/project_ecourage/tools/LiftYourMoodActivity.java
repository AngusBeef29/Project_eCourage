package com.example.sfene_000.project_ecourage.tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;
import android.view.MotionEvent;
import android.app.Activity;

import com.example.sfene_000.project_ecourage.R;

public class LiftYourMoodActivity extends AppCompatActivity {
    ViewFlipper VF;
    float X1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_your_mood);
        VF = (ViewFlipper) findViewById(R.id.viewFlipper);
        VF.setInAnimation(this,android.R.anim.slide_out_right);
        VF.setOutAnimation(this,android.R.anim.slide_in_left);
    }
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                X1 = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (X1 > finalX) {
                    if (VF.getDisplayedChild() == 1)
                        break;
                    VF.showNext();
                } else {
                    if (VF.getDisplayedChild() == 0)
                        break;
                    VF.showPrevious();
                }
                break;
        }
        return false;
    }
}
