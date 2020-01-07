package com.hzqiyunkeji.ec.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hzqiyunkeji.core.delegates.LatteDelegate;
import com.hzqiyunkeji.core.net.RestClient;
import com.hzqiyunkeji.core.net.callback.IError;
import com.hzqiyunkeji.core.net.callback.ISuccess;
import com.hzqiyunkeji.core.util.timer.BaseTimerTask;
import com.hzqiyunkeji.core.util.timer.ITimerListener;
import com.hzqiyunkeji.ec.R;

import java.text.MessageFormat;
import java.util.Timer;

import static android.content.ContentValues.TAG;


/**
 * Created by 傅令杰 on 2017/4/22
 */

public class SignUpDelegate extends LatteDelegate implements ITimerListener,View.OnClickListener {


    private final static String loginUrl="https://sso-qa.hzqykeji.com/";

    private String phone =null;

    private String code=null;

    TextInputEditText mPhone = null;

    TextInputEditText mCode=null;
    AppCompatButton mButton=null;

    AppCompatTextView getCode=null;

    private Timer mTimer = null;
    private int mCount = 5;


    private boolean checkPhone() {
        phone = mPhone.getText().toString();
        boolean isPass = true;
        if (phone.isEmpty() || phone.length() != 11) {
            Toast.makeText(getContext(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
            isPass = false;
        }
        return isPass;
    }

    private boolean checkCode() {
        code = mCode.getText().toString();
        boolean isPass = true;
        if (code.isEmpty() || code.length() !=4) {
            Toast.makeText(getContext(),"请输入验证码",Toast.LENGTH_SHORT).show();
            isPass = false;
        }
        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mPhone=rootView.findViewById(R.id.edit_sign_up_phone);
        mCode=rootView.findViewById(R.id.edit_sign_up_code);
        mButton=rootView.findViewById(R.id.btn_sign_up);
        mButton.setOnClickListener(this);
        getCode=rootView.findViewById(R.id.get_code);
        getCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.get_code){
            if(checkPhone()){
                RestClient
                        .builder()
                        .url(loginUrl+"captcha/"+phone)
                        .headers("Authorization","d99a81e4869b667ac93476ce026a0366")
                        .params("type","login")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d(TAG, "onSuccess: "+response);
                                Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                            }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, " onError: "+msg);
                        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    }
                }).build().get();
                mTimer=new Timer();
                final BaseTimerTask task = new BaseTimerTask(this);
                mTimer.schedule(task, 0, 1000);
            }
        }else if(view.getId()==R.id.btn_sign_up){
            if(checkPhone()&&checkCode()){
                RestClient.builder().loader(getContext())
                        .url(loginUrl+"login/mobile")
                        .headers("Authorization","d99a81e4869b667ac93476ce026a0366")
                        .params("mobile",phone)
                        .params("captcha", code)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                            }
                        }).error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                            }
                        }).build().post();
            }
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getCode != null) {
                    getCode.setText(MessageFormat.format("重新获取{0}S", mCount));
                    mCount--;
                    if (mCount < 0) {
                        getCode.setText("获取验证码");
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            mCount=5;
                        }
                    }
                }
            }
        });
    }
}
