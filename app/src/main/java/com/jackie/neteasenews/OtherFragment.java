package com.jackie.neteasenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jackie on 2015/12/29.
 * 其他
 */
public class OtherFragment extends Fragment {
    private String mTitle;

    public OtherFragment(String title) {
        this.mTitle = title;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        TextView textView = (TextView) view.findViewById(R.id.other);
        textView.setText(mTitle);
        return view;
    }
}
