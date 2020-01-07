package com.hzqiyunkeji.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;

import com.hzqiyunkeji.core.delegates.LatteDelegate;
import com.hzqiyunkeji.ec.R;


/**
 * Created by 傅令杰 on 2017/4/22
 */

public class SignUpDelegate extends LatteDelegate implements View.OnClickListener {



    TextInputEditText mPhone = null;


    AppCompatButton mButton=null;


    private boolean checkForm() {
        final String phone = mPhone.getText().toString();
        boolean isPass = true;
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
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
        mButton=rootView.findViewById(R.id.btn_sign_up);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_sign_up){
            checkForm();
        }
    }
}
