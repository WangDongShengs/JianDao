package com.wds.base;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.wds.common.UserApplication;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceSubscriber <T> {
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
    public abstract void onSuccess(T t);
    @Override
    public void onError(Throwable e) {
        Log.e("TAG", "error:" + e.getMessage());
        //对异常进行分类,不同的异常提示用户不同的信息
        if (e instanceof HttpException) {
            //   HTTP错误
            onFail("HTTP错误");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onFail("连接错误");
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onFail("连接超时");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onFail("解析错误");
        } else {
            if (e != null) {
                onFail(e.toString());
            } else {
                onFail("未知错误");
            }
        }
    }

    protected  void onFail(String error){
        Toast.makeText(UserApplication.getUserApplication(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }
}
