package com.wds.base;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wds.common.UserApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bind = ButterKnife.bind(this);
        initMVP();
        initView();
        initData();
        initListener();
    }
    protected void toast(String mag){
        Toast.makeText(UserApplication.getUserApplication(), mag, Toast.LENGTH_SHORT).show();
    }
    protected void initMVP() {
    }

    protected void initListener() {

    }

    protected abstract int getLayout();

    protected void initView() {

    }

    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
