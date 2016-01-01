package com.jackie.neteasenews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jackie on 2016/1/1.
 */
public class IndicatorTextView extends TextView {
    private boolean mIsRemovedFromAllItemList;  //标记是否从候选条目中移除

    public IndicatorTextView(Context context) {
        this(context, null);
    }

    public IndicatorTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isRemovedFromAllItemList() {
        return mIsRemovedFromAllItemList;
    }

    public void setIsRemovedFromAllItemList(boolean isRemovedFromAllItemList) {
        this.mIsRemovedFromAllItemList = isRemovedFromAllItemList;
    }
}
