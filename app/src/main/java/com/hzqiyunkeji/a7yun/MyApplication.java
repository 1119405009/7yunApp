package com.hzqiyunkeji.a7yun;

import android.app.Application;

import com.hzqiyunkeji.core.app.Latte;
import com.hzqiyunkeji.core.net.interceptors.DebugInterceptor;
import com.hzqiyunkeji.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://travel-platform-api-qa.hzqykeji.com/")
                .withLoaderDelayed(1000)
                 .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

    }
}
