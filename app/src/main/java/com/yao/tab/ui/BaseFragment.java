package com.yao.tab.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() ----- this:" + this.getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() ----- this:" + this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() ----- this:" + this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() ----- this:" + this.getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() ----- this:" + this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() ----- this:" + this.getClass().getSimpleName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint() ----- " + this.getClass().getSimpleName() + ", isVisibleToUser:" + isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        boolean result = super.getUserVisibleHint();
        Log.i(TAG, "getUserVisibleHint() ----- result:" + result);
        return result;
    }

}
