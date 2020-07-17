package com.wds.jiandao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wds.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {
    @BindView(R.id.iv_cl_set)
    ImageView ivClSet;
    @BindView(R.id.tv_cl_set)
    TextView tvClSet;
    @BindView(R.id.rl_set_a)
    RelativeLayout rlSetA;
    @BindView(R.id.rl_set_b)
    RelativeLayout rlSetB;
    @BindView(R.id.rl_set_c)
    RelativeLayout rlSetC;
    @BindView(R.id.rl_set_d)
    RelativeLayout rlSetD;
    @BindView(R.id.rl_set_e)
    RelativeLayout rlSetE;
    @BindView(R.id.rl_set_f)
    RelativeLayout rlSetF;
    @BindView(R.id.rl_set_g)
    RelativeLayout rlSetG;
    @BindView(R.id.rl_set_h)
    RelativeLayout rlSetH;
    @BindView(R.id.btn_set_item)
    Button btnSetItem;

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }


    @OnClick({R.id.iv_cl_set, R.id.btn_set_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cl_set:
                finish();
                break;
            case R.id.btn_set_item:
                Intent intent = new Intent(SetActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
