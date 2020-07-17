package com.wds.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wds.adapter.SpecialAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.SpecialBean;
import com.wds.jiandao.R;
import com.wds.presenter.SpecialPresenter;
import com.wds.util.MvpInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends BaseMVPFragment<SpecialPresenter, MvpInterface.SpecialView> implements MvpInterface.SpecialView {


    @BindView(R.id.tv_cl_video)
    TextView tvClVideo;
    @BindView(R.id.rl_cl_special)
    RecyclerView rlClSpecial;
    private SpecialAdapter specialAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @Override
    protected MvpInterface.SpecialView initMVPView() {
        return this;
    }

    @Override
    protected SpecialPresenter initMVPPresenter() {
        return new SpecialPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        rlClSpecial.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rlClSpecial.setLayoutManager(new LinearLayoutManager(getActivity()));
        specialAdapter = new SpecialAdapter(getActivity());
        rlClSpecial.setAdapter(specialAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.SpecialPresenter();
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        List<SpecialBean.DataBean.BannerListBean> banner_list = specialBean.getData().getBanner_list();
        List<SpecialBean.DataBean.ListBean> list = specialBean.getData().getList();
        specialAdapter.setBanner_Bean(banner_list);
        specialAdapter.setList_bean(list);
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
