package com.fzj.functiondemo.binding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.fzj.functiondemo.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior;

public class HeaderBehavior extends ScrollingViewBehavior implements CoordinatorLayout.AttachedBehavior {

    public HeaderBehavior() {
    }

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency.getId() == R.id.ll_header;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (dependency.getBottom() > 0 || child.getScrollY() == 0) {
            child.setNestedScrollingEnabled(false);
            return true;
        } else {
            child.setNestedScrollingEnabled(true);
            return true;
        }
    }

    @NonNull
    @Override
    public CoordinatorLayout.Behavior getBehavior() {
        return this;
    }
}
