package com.fzj.functiondemo.binding.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 可拖拽的layout
 */
public class DragableNestedScrollView extends NestedScrollView {

    public DragableNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public DragableNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragableNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private int scrollY = -1;
    private float downY = 0;
    private float moveY = 0;

    @Override
    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    public void setDownY(float downY) {
        this.downY = downY;
    }

    public void setMoveY(float moveY) {
        this.moveY = moveY;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getChildAt(0).offsetTopAndBottom((int) scrollY);
        if (getScrollY() == 0)
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    downY = ev.getY();
                    return true;
                }
                case MotionEvent.ACTION_MOVE: {
                    scrollY = (int) (ev.getY() - downY);
                    getChildAt(0).offsetTopAndBottom((int) scrollY);
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    TranslateAnimation anim = new TranslateAnimation(0, 0,
                            getChildAt(0).getTop(), 0);
                    anim.setDuration(100);
                    getChildAt(0).startAnimation(anim);
                    getChildAt(0).layout(getChildAt(0).getLeft(), 0,
                            getChildAt(0).getRight(), getChildAt(0).getHeight());
                    return true;
                }
            }
        return super.dispatchTouchEvent(ev);
    }

}
