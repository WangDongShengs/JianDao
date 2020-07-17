package com.wds.jiandao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wds.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_login_cl)
    ImageView ivLoginCl;
    @BindView(R.id.iv_login)
    ImageView ivLogin;
    @BindView(R.id.tv_cl_nu)
    TextView tvClNu;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.tv_login_code)
    TextView tvLoginCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_text_denglu)
    TextView tvTextDenglu;
    @BindView(R.id.tv_text_yanzhengma)
    TextView tvTextYanzhengma;
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
        return R.layout.activity_register;
    }


    @OnClick({R.id.iv_login_cl, R.id.btn_login, R.id.tv_text_denglu, R.id.tv_text_yanzhengma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_cl:
                finish();
                break;
            case R.id.btn_login:
                break;
            case R.id.tv_text_denglu:
                Intent intent = new Intent(RegisterActivity.this, PasswordLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_text_yanzhengma:
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
