package com.jackie.neteasenews.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jackie.neteasenews.R;

/**
 * Created by Jackie on 2015/12/29.
 * 其他
 */
public class MoreFragment extends Fragment {
    private String mTitle;

    public MoreFragment() {
        this.mTitle = "";
    }

    public MoreFragment(String title) {
        this.mTitle = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        TextView textView = (TextView) view.findViewById(R.id.more);
        textView.setText(mTitle);
        return view;
    }
}
