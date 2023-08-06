package com.yao.tab.frame;

import android.view.LayoutInflater;
import android.view.View;

import com.yao.tab.R;

public class ContactFrame extends BaseFrame {

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater) {
        if (getView() == null) {
            View view = inflater.inflate(R.layout.frame_contact, null);
            setView(view);
        }
        return getView();
    }
}
