package com.example.rinkb;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class StepsSeekBar extends SeekBar {

    int mSteps;
    int mCurrentStep;
    int mStepWidth;

    public StepsSeekBar(Context context, AttributeSet attrs, int steps) {
        super(context, attrs);
        mSteps = steps;
        mCurrentStep = 0;
        mStepWidth = 100 / mSteps;
    }

    public void nextStep() {
        // Animate progress to next step
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", mStepWidth * mCurrentStep, mStepWidth * (++mCurrentStep));
        animator.setDuration(50);
        animator.start();
    }
}