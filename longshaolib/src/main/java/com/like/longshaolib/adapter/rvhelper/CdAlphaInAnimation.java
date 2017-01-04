package com.like.longshaolib.adapter.rvhelper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;


/**
 * item、淡入的动画效果
 */
public class CdAlphaInAnimation implements CdBaseAnimation {

    private static final float DEFAULT_ALPHA_FROM = 0f;
    private final float mFrom;

    public CdAlphaInAnimation() {
        this(DEFAULT_ALPHA_FROM);
    }

    public CdAlphaInAnimation(float from) {
        mFrom = from;
    }

    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", mFrom, 1f)};
    }
}
