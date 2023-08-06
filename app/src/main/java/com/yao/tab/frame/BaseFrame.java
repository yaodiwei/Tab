package com.yao.tab.frame;

import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseFrame {

    private View contentView;

    protected void onCreate() {

    }

    public View onCreateView(LayoutInflater inflater) {
        return null;
    }

    public View getView() {
        return contentView;
    }

    protected void setView(View content) {
        this.contentView = content;
    }
}
