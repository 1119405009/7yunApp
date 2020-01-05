package com.hzqiyunkeji.a7yun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hzqiyunkeji.core.delegates.LatteDelegate;
import com.hzqiyunkeji.core.net.RestClient;
import com.hzqiyunkeji.core.net.callback.IError;
import com.hzqiyunkeji.core.net.callback.ISuccess;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public class AppDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }



    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
       testRestClient();
    }

    private void testRestClient() {
        RestClient.builder().loader(getContext()).url("https://www.baidu.com/?tn=93153557_hao_pg").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).build().get();
    }


}
