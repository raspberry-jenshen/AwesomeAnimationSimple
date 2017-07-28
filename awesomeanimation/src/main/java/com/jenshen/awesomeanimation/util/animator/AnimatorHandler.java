package com.jenshen.awesomeanimation.util.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimatorHandler {

    private static final String TAG = "AwesomeAnimation: " + AnimatorHandler.class.getSimpleName();

    private List<AnimatorWrapper> animatorList;
    private boolean onPause;

    private AnimatorListenerAdapter animatorListenerAdapter;

    public void addAnimator(final Animator animator) {
        final List<AnimatorWrapper> animators = createListAnimatorsInNeeded();
        animators.add(new AnimatorWrapper(animator));
        animator.addListener(animatorListenerAdapter);
    }

    public void removeAnimator(final Animator animator) {
        if (animatorList != null) {
            for (AnimatorWrapper animatorWrapper : animatorList) {
                if (AnimatorUtil.equalsAnimators(animatorWrapper.getAnimator(), animator)) {
                    animatorWrapper.clear();
                    animatorList.remove(animatorWrapper);
                    return;
                }
            }
        }
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus) {
            onResume();
        } else {
            onPause();
        }
    }

    public void onVisibilityChanged(int visibility) {
        if (visibility == View.VISIBLE) {
            onResume();
        } else {
            onPause();
        }
    }

    public boolean isOnPause() {
        return onPause;
    }

    public void onResume() {
        if (!onPause) {
            return;
        }
        onPause = false;
        Log.d(TAG, "onResume");
        if (this.animatorList != null) {
            for (AnimatorWrapper animator : this.animatorList) {
                animator.onResume();
            }
        }
    }

    public void onPause() {
        if (onPause) {
            return;
        }
        onPause = true;
        Log.d(TAG, "onPause");
        if (this.animatorList != null) {
            for (AnimatorWrapper animator : this.animatorList) {
                animator.onPause();
            }
        }
    }

    public void cancel() {
        Log.d(TAG, "cancel");
        if (this.animatorList != null) {
            for (AnimatorWrapper animator : animatorList) {
                animator.cancel();
            }
            this.animatorList.clear();
            this.animatorList = null;
        }
    }

    private List<AnimatorWrapper> createListAnimatorsInNeeded() {
        if (this.animatorList == null) {
            this.animatorList = new CopyOnWriteArrayList<>();
            this.animatorListenerAdapter = new AnimatorListenerAdapter() {

                @Override
                public void onAnimationCancel(Animator animation) {
                    removeAnimator(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    removeAnimator(animation);
                }
            };
        }
        return this.animatorList;
    }
}
