package com.yao.tab.frame;

import android.view.LayoutInflater;
import android.view.View;
import com.yao.tab.R;

public class ChatFrame extends BaseFrame {

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater) {
        if (getView() == null) {
            View view = inflater.inflate(R.layout.frame_chat, null);
            setView(view);
        }
        return getView();
    }
}
