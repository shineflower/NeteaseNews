package com.jackie.neteasenews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jackie on 2015/12/30.
 * 流式布局
 */
public class FrameLayout extends ViewGroup {
    public FrameLayout(Context context) {
        this(context, null);
    }

    public FrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有子控件的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            if (lineWidth + child.getMeasuredWidth() < width) {
            } else {
                lineWidth = 0;
                lineHeight += child.getMeasuredHeight();
            }

            child.layout(lineWidth, lineHeight, lineWidth + child.getMeasuredWidth(), lineHeight + child.getMeasuredHeight());
            lineWidth += child.getMeasuredWidth();
        }
    }
}
