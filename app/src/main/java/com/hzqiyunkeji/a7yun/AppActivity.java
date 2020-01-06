package com.hzqiyunkeji.a7yun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.hzqiyunkeji.core.activities.ProxyActivity;
import com.hzqiyunkeji.core.delegates.LatteDelegate;
import com.hzqiyunkeji.ec.launcher.LauncherDelegate;
import com.hzqiyunkeji.ec.launcher.LauncherScrollDelegate;


public class AppActivity extends ProxyActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
