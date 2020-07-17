package com.wds.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wds.base.BaseFragment;
import com.wds.jiandao.LoginActivity;
import com.wds.jiandao.R;
import com.wds.jiandao.SetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.btn_me_item)
    Button btnMeItem;
    Unbinder unbinder;
    @BindView(R.id.cl_rl_image)
    ImageView clRlImage;
    @BindView(R.id.cl_rl_tv1)
    TextView clRlTv1;
    @BindView(R.id.cl_rl)
    RelativeLayout clRl;
    @BindView(R.id.iv_rl_jifeng)
    ImageView ivRlJifeng;
    @BindView(R.id.cl_ll_jifeng)
    RelativeLayout clLlJifeng;
    @BindView(R.id.iv_rl_start)
    ImageView ivRlStart;
    @BindView(R.id.cl_ll_start)
    RelativeLayout clLlStart;
    @BindView(R.id.iv_rl_mes)
    ImageView ivRlMes;
    @BindView(R.id.cl_ll_mes)
    RelativeLayout clLlMes;
    @BindView(R.id.iv_rl_set)
    ImageView ivRlSet;
    @BindView(R.id.cl_ll_set)
    RelativeLayout clLlSet;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }




    @OnClick({R.id.cl_ll_jifeng, R.id.cl_ll_start, R.id.cl_ll_mes, R.id.cl_ll_set, R.id.btn_me_item,R.id.cl_rl_image})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        switch (view.getId()) {
            case R.id.cl_rl_image:
                startActivity(intent);
                break;
            case R.id.cl_ll_jifeng:
                startActivity(intent);
                break;
            case R.id.cl_ll_start:
                startActivity(intent);
                break;
            case R.id.cl_ll_mes:
                startActivity(intent);
                break;
            case R.id.cl_ll_set:
                Intent intent1 = new Intent(getActivity(), SetActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_me_item:
                startActivity(intent);
                break;
        }
    }
}
