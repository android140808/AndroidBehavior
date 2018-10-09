package com.avater.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/10/9.
 */

public class TittleBehavior extends CoordinatorLayout.Behavior<View> {
    /**
     * TextView 与 RecyclerView重合时的距离
     */
    private float deltay;

    public TittleBehavior() {
    }

    public TittleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * View要依赖的View的类型
     *
     * @param parent     CoordinatorLayout
     * @param child      使用这个类的View
     * @param dependency 要监听的View(这里为RecyclerView)
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.e("TAG", "layoutDependsOn()");
        return dependency instanceof RecyclerView;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        Log.e("TAG", "onDependentViewRemoved()");
        super.onDependentViewRemoved(parent, child, dependency);
    }

    /**
     * 当被监听的View状态变化时会调用该方法
     *
     * @param parent     CoordinatorLayout
     * @param child      TextView(当前)
     * @param dependency RecycleView
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.e("TAG", "onDependentViewChanged()");
        if (deltay == 0) {
            deltay = dependency.getY() - child.getHeight();
            Log.e("TAG", "deltay == " + deltay);
        }
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        Log.e("TAG", "dy == " + dy);
        float y = -(dy / deltay) * child.getHeight();
        Log.e("TAG", "y == " + y);
        child.setTranslationY(y);
//        float alpha = 1 - (dy / deltay);
//        child.setAlpha(alpha);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }


}
