package com.wds.util;

import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wds.common.UserApplication;

import java.util.Map;

public class LoginUtil {
    /*//第三方登录
    public void login(SHARE_MEDIA media) {
        UMShareAPI umShareAPI = UMShareAPI.get(UserApplication.getUserApplication());
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(, media, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        *//**
         * @desc 授权开始的回调
         * @param platform 平台名称
         *//*
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.e("TAG", platform.getName() + ":LoginActivity onStart()");
        }

        *//**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         *//*
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            logMap(data);
            Toast.makeText(UserApplication.getUserApplication(), "成功了", Toast.LENGTH_LONG).show();
            Log.e("TAG", platform.getName() + "LoginActivity onComplete()");
        }

        *//**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         *//*
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.e("TAG", platform.getName() + "LoginActivity onError()");
            Toast.makeText(UserApplication.getUserApplication(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        *//**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         *//*
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.e("TAG", platform.getName() + "LoginActivity onCancel()");
            Toast.makeText(UserApplication.getUserApplication(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Log.e("TAG", "logMap: " + entry.getKey() + "," + entry.getValue());
        }
    }*/
}
