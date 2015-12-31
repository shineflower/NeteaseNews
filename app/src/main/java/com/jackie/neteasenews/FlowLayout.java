package com.jackie.neteasenews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jackie on 2015/12/30.
 * 流式布局
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        //当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = getPaddingLeft();
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            if (lineWidth + child.getMeasuredWidth() + params.leftMargin + params.rightMargin  < width) {

            } else {
                lineWidth = getPaddingLeft();
                lineHeight += child.getMeasuredHeight() + params.topMargin;
            }

            child.layout(lineWidth, lineHeight, lineWidth + child.getMeasuredWidth(), lineHeight + child.getMeasuredHeight());
            lineWidth += child.getMeasuredWidth() + params.leftMargin;
        }
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
