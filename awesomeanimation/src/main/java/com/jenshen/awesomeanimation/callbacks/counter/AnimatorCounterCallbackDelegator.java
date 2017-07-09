package com.jenshen.awesomeanimation.callbacks.counter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;

import com.jenshen.awesomeanimation.callbacks.AnimatorCallbackDelegator;

public class AnimatorCounterCallbackDelegator extends AnimatorCallbackDelegator {
    private int animationsCount = 0;
    private int countEnd = 0;
    private int countCancel = 0;
    private int countRepeat = 0;
    private int countStart = 0;
    private int countPause = 0;
    private int countResume = 0;

    @Override
    public void addListener(@NonNull AnimatorListenerAdapter adapter) {
        animationsCount++;
        super.addListener(adapter);
    }

    @Override
    public void removeListener(@NonNull AnimatorListenerAdapter adapter) {
        animationsCount--;
        super.removeListener(adapter);
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        countCancel++;
        if (countCancel == animationsCount) {
            super.onAnimationCancel(animation);
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        countEnd++;
        if (countEnd == animationsCount) {
            super.onAnimationEnd(animation);
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        countRepeat++;
        if (countRepeat == animationsCount) {
            super.onAnimationRepeat(animation);
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {
        countStart++;
        if (countStart == animationsCount) {
            super.onAnimationStart(animation);
        }
    }

    @Override
    public void onAnimationPause(Animator animation) {
        countPause++;
        if (countPause == animationsCount) {
            super.onAnimationPause(animation);
        }
    }

    @Override
    public void onAnimationResume(Animator animation) {
        countResume++;
        if (countResume == animationsCount) {
            super.onAnimationResume(animation);
        }
    }
}