package com.wds.jiandao;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wds.base.BaseActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.iv_login_cl)
    ImageView ivLoginCl;
    @BindView(R.id.iv_login)
    ImageView ivLogin;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.tv_login_code)
    TextView tvLoginCode;
    @BindView(R.id.tv_text_a)
    TextView tvTextA;
    @BindView(R.id.tv_text_user)
    TextView tvTextUser;
    @BindView(R.id.tv_text_b)
    TextView tvTextB;
    @BindView(R.id.tv_text_ys)
    TextView tvTextYs;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_text_zhuce)
    TextView tvTextZhuce;
    @BindView(R.id.tv_text_denglu)
    TextView tvTextDenglu;
    @BindView(R.id.iv_login_wechat)
    ImageView ivLoginWechat;
    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;
    @BindView(R.id.iv_login_sina)
    ImageView ivLoginSina;
    @BindView(R.id.tv_login_shejiao)
    TextView tvLoginShejiao;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.tv_text_denglu)
    public void onViewClicked() {

    }

    @Override
    protected void initView() {
        super.initView();
        initPermission();
    }

    private void initPermission() {
        String[] pers = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(this, pers, 100);
    }

    @OnClick({R.id.iv_login_cl, R.id.btn_login, R.id.tv_text_zhuce, R.id.tv_text_denglu,R.id.iv_login_wechat, R.id.iv_login_qq, R.id.iv_login_sina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_cl:
                finish();
                break;
            case R.id.btn_login:
                break;
            case R.id.tv_text_zhuce:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_text_denglu:
                intent = new Intent(LoginActivity.this, PasswordLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_login_wechat:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_login_qq:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_login_sina:

                login(SHARE_MEDIA.SINA);
                break;
        }
    }
    //第三方登录
    public void login(SHARE_MEDIA media) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(this, media, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.e("TAG", platform.getName() + ":LoginActivity onStart()");
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            logMap(data);
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Log.e("TAG", platform.getName() + "LoginActivity onComplete()");
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.e("TAG", platform.getName() + "LoginActivity onError()");
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.e("TAG", platform.getName() + "LoginActivity onCancel()");
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Log.e("TAG", "logMap: " + entry.getKey() + "," + entry.getValue());
        }
    }
}
