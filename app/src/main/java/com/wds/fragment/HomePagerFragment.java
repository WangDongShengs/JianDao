package com.wds.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wds.adapter.HomeAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.HomeBean;
import com.wds.jiandao.R;
import com.wds.presenter.HomePresenter;
import com.wds.util.MvpInterface;

import java.util.List;

import butterknife.BindView;


public class HomePagerFragment extends BaseMVPFragment<HomePresenter, MvpInterface.HomeView> implements MvpInterface.HomeView {

    @BindView(R.id.rv_cl_home)
    RecyclerView rvClHome;
    String id;
    private HomeAdapter homeAdapter;

    @Override
    protected MvpInterface.HomeView initMVPView() {
        return this;
    }

    @Override
    protected HomePresenter initMVPPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        rvClHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvClHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(getActivity());
        rvClHome.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        id=getArguments().getString("id");
        presenter.HomePresenter(id);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        List<HomeBean.DataBean.ArticleListBean> article_list = homeBean.getData().getArticle_list();
        List<HomeBean.DataBean.BannerListBean> banner_list = homeBean.getData().getBanner_list();
        List<HomeBean.DataBean.FlashListBean> flash_list = homeBean.getData().getFlash_list();
        homeAdapter.setBanner_list(banner_list);
        homeAdapter.setFlash_list(flash_list);
        homeAdapter.setArticle_list(article_list);
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
