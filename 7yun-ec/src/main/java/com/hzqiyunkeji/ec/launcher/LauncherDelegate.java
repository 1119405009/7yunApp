package com.hzqiyunkeji.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.hzqiyunkeji.core.delegates.LatteDelegate;

import com.hzqiyunkeji.core.ui.launcher.ScrollLauncherTag;
import com.hzqiyunkeji.core.util.storage.LattePreference;
import com.hzqiyunkeji.core.util.timer.BaseTimerTask;
import com.hzqiyunkeji.core.util.timer.ITimerListener;
import com.hzqiyunkeji.ec.R;


import java.text.MessageFormat;
import java.util.Timer;




/**
 * Created by 傅令杰 on 2017/4/22
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener, View.OnClickListener {


    private AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
        mTvTimer = rootView.findViewById(R.id.tv_launcher_timer);
        mTvTimer.setOnClickListener(this);
    }


    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {

        }
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() != R.id.tv_launcher_timer) {
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
                checkIsShowScroll();
            }
          }


    }
}
