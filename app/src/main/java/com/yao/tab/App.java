package com.yao.tab;

import android.app.Application;

import com.yao.tab.util.AppUtil;

/**
 * @author Yao
 * @date 2016/7/23
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.init(this);

    }
}
