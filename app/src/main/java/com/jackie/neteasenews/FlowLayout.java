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
        /**
         * MeasureSpec.EXACTLY        //match_parent或者精确值
           MeasureSpec.AT_MOST        //warp_content 这种情况的View的宽度和高度是需要我们自己计算的
           MeasureSpec.UNSPECIFIED   //少见，子控件想要多大就多大
         */
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        //记录每一行的高度和宽度
        int lineWidth = getPaddingLeft();
        int lineHeight = getPaddingBottom();

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            //测量子View的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            //子View占据的宽度
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            //子View占据的高度
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            if (lineWidth + childWidth < sizeWidth - getPaddingLeft() - getPaddingRight()) {  //未换行
                //叠加行宽
                lineWidth += childWidth;
                //得到当前行的最大高度(同一行中所有子View中的最大高度)
                lineHeight = Math.max(lineHeight, childHeight);
            } else {   //换行
                /**
                 * 换行之后，改行的宽和高就能确定
                 */
                //对比得到最大的宽度(所有行中的最大行宽)
                width = Math.max(width, lineWidth);
                //记录高度
                height += lineHeight;

                //重置lineWidth和lineHeight
                lineWidth = childWidth;
                lineHeight = childHeight;
            }

            //最后一个控件
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                             modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = getPaddingLeft();
        int lineHeight = getPaddingTop();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            if (lineWidth + childWidth + params.leftMargin + params.rightMargin  < width - getPaddingLeft() - getPaddingRight()) {

            } else {
                lineWidth = getPaddingLeft();
                lineHeight += childHeight + params.topMargin + params.bottomMargin;
            }

            child.layout(lineWidth, lineHeight, lineWidth + childWidth, lineHeight + childHeight);
            lineWidth += childWidth + params.leftMargin + params.rightMargin;
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
