package com.yao.tab.util;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.yao.tab.R;

import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSwitchTool implements OnClickListener {

    public interface OnClickListener {
        void onClick(View view);
    }
    public interface OnDoubleClickListener {
        void onDoubleClick(View view);
    }


    private FragmentManager mFragmentManager;
    private int mContainerId;
    private boolean mShowAnimator;
    private List<View> mClickableViews; //传入用于被点击的view,比如是一个LinearLayout
    private Fragment[] mFragments;
    private OnClickListener mOnClickListener;
    private OnDoubleClickListener mOnDoubleOnClickListener;

    private Fragment mCurrentFragment;
    private View mCurrentSelectedView;

    public void setClickableViews(View... clickableViews) {
        this.mClickableViews = Arrays.asList(clickableViews);
        for (View view : clickableViews) {
            view.setOnClickListener(this);
        }
    }

    public void setClickableViewsAndListener(OnClickListener onClickListener, View... clickableViews) {
        mOnClickListener = onClickListener;
    }

    public void setFragments(Fragment... fragments) {
        this.mFragments = fragments;
    }

    public void changeTag(View targetView) {

        if (targetView == mCurrentSelectedView) {
            return;
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment targetFragment = mFragmentManager.findFragmentByTag(String.valueOf(targetView.getId()));
        int targetPosition = mClickableViews.indexOf(targetView);
        int currentPosition = mClickableViews.indexOf(mCurrentSelectedView);

        if (mShowAnimator) {
            if (targetPosition > currentPosition) {
                fragmentTransaction.setCustomAnimations(R.animator.slide_right_in, R.animator.slide_left_out);
            } else if (targetPosition < currentPosition) {
                fragmentTransaction.setCustomAnimations(R.animator.slide_left_in, R.animator.slide_right_out);
            }
        }


        if (targetFragment == null) {
            if (mCurrentFragment != null) {
                fragmentTransaction.hide(mCurrentFragment);
                mCurrentSelectedView.setSelected(false);
            }
            targetFragment = mFragments[targetPosition];

            fragmentTransaction.add(mContainerId, targetFragment, String.valueOf(targetView.getId()));
            //fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.STARTED);
        } else {
            fragmentTransaction.hide(mCurrentFragment);
            mCurrentSelectedView.setSelected(false);
            fragmentTransaction.show(targetFragment);
        }

        fragmentTransaction.commit();
        mCurrentFragment = targetFragment;
        mCurrentSelectedView = targetView;
        mCurrentSelectedView.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        changeTag(v);
    }

    public static final class Builder {
        FragmentManager mFragmentManager;
        int mContainerId;
        boolean mShowAnimator;
        List<View> mClickableViews; //传入用于被点击的view,比如是一个LinearLayout
        Fragment[] mFragments;
        OnClickListener mOnClickListener;
        OnDoubleClickListener mOnDoubleClickListener;

        /**
         * 必要参数 Fragment管理类
         * @param fragmentManager fragmentManager
         * @return Builder
         */
        public Builder fragmentManager(FragmentManager fragmentManager) {
            this.mFragmentManager = fragmentManager;
            return this;
        }

        /**
         * 必要参数 fragment容器的布局id
         * @param containerId containerId
         * @return Builder
         */
        public Builder containerId(int containerId) {
            this.mContainerId = containerId;
            return this;
        }

        /**
         * 必要参数 Tab里的可点击控件
         * @param clickableViews clickableViews
         * @return Builder
         */
        public Builder clickableViews(View... clickableViews) {
            this.mClickableViews = Arrays.asList(clickableViews);
            return this;
        }

        /**
         * 必要参数 Tab对应的Fragment
         * @param fragments fragments
         * @return Builder
         */
        public Builder fragments(Fragment... fragments) {
            this.mFragments = fragments;
            return this;
        }

        /**
         * 可选参数 是否显示动画
         * @param showAnimator showAnimator
         * @return Builder
         */
        public Builder showAnimator(boolean showAnimator) {
            this.mShowAnimator = showAnimator;
            return this;
        }

        /**
         * 可选参数 Tab的单击事件
         * @param onClickListener onClickListener
         * @return Builder
         */
        public Builder onClickListener(OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        /**
         * 可选参数 Tab的双击事件
         * @param onDoubleClickListener onDoubleClickListener
         * @return Builder
         */
        public Builder onDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
            this.mOnDoubleClickListener = onDoubleClickListener;
            return this;
        }

        public FragmentSwitchTool build() {
            final FragmentSwitchTool fragmentSwitchTool = new FragmentSwitchTool();
            fragmentSwitchTool.mFragmentManager = mFragmentManager;
            fragmentSwitchTool.mContainerId = mContainerId;
            fragmentSwitchTool.mShowAnimator = mShowAnimator;
            fragmentSwitchTool.mClickableViews = mClickableViews;
            fragmentSwitchTool.mFragments = mFragments;
            fragmentSwitchTool.mOnClickListener = mOnClickListener;
            fragmentSwitchTool.mOnDoubleOnClickListener = mOnDoubleClickListener;

            if (mOnDoubleClickListener == null) {
                for (View view : mClickableViews) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fragmentSwitchTool.changeTag(view);
                            if (mOnClickListener != null) {
                                mOnClickListener.onClick(view);
                            }
                        }
                    });
                }
            } else {
                for (final View view : mClickableViews) {
                    final GestureDetector gestureDetector = new GestureDetector(mClickableViews.get(0).getContext(), new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            Log.e("YAO", "onSingleTapUp:" + e.getAction());
                            fragmentSwitchTool.changeTag(view);
                            if (fragmentSwitchTool.mOnClickListener != null) {
                                fragmentSwitchTool.mOnClickListener.onClick(view);
                            }
                            return true;
                        }

                        @Override
                        public boolean onDown(MotionEvent e) {
                            return true;
                        }

                        @Override
                        public boolean onDoubleTap(MotionEvent e) {
                            fragmentSwitchTool.mOnDoubleOnClickListener.onDoubleClick(view);
                            return true;
                        }
                    });

                    view.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return gestureDetector.onTouchEvent(event);
                        }
                    });
                }
            }
            return fragmentSwitchTool;
        }
    }
}
