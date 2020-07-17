package com.wds.fragment;


import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wds.adapter.NavigationAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.HomeTabBean;
import com.wds.jiandao.R;
import com.wds.presenter.HomeTabPresenter;
import com.wds.util.Logger;
import com.wds.util.MvpInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMVPFragment<HomeTabPresenter, MvpInterface.HomeTabView> implements MvpInterface.HomeTabView {


    @BindView(R.id.iv_cl_home)
    ImageView ivClHome;
    @BindView(R.id.iv_cl_home_s)
    ImageView ivClHomeS;
    @BindView(R.id.navigation_home)
    NavigationView navigationHome;
    @BindView(R.id.drawer_home)
    DrawerLayout drawerHome;
    @BindView(R.id.tab_cl_home)
    TabLayout tabClHome;
    @BindView(R.id.vp_cl_home)
    ViewPager vpClHome;
    @BindView(R.id.recycler_home)
    RecyclerView recyclerHome;
    private NavigationAdapter navigationAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        //抽屉锁模式
        drawerHome.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
       // Logger.d("ddd   %s","name");

    }


    @OnClick(R.id.iv_cl_home)
    public void onViewClicked() {

        //判断打开的时候，将其关闭，反之打开
        if (drawerHome.isDrawerOpen(Gravity.LEFT)) {
            //关闭侧滑菜单
            drawerHome.closeDrawer(Gravity.LEFT);
        } else {
            //打开侧滑菜单
            drawerHome.openDrawer(Gravity.LEFT);
        }

    }

    @Override
    protected MvpInterface.HomeTabView initMVPView() {
        return this;
    }

    @Override
    protected HomeTabPresenter initMVPPresenter() {
        return new HomeTabPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.HomeTabPresenter();
    }

    @Override
    public void onSuccess(HomeTabBean homeTabBean) {

        final List<Fragment> fragments = new ArrayList<>();

       List<HomeTabBean.DataBean.ListBean> list = homeTabBean.getData().getList();
        //navigation 中的recycler 的佈局
        recyclerHome.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        navigationAdapter = new NavigationAdapter(list, getActivity());
        recyclerHome.setAdapter(navigationAdapter);

        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i).getId();
            HomePagerFragment homePagerFragment = new HomePagerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            homePagerFragment.setArguments(bundle);
            fragments.add(homePagerFragment);
        }
        vpClHome.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabClHome.setupWithViewPager(vpClHome);
        tabClHome.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            tabClHome.getTabAt(i).setText(name);
        }

    }

    @Override
    public void onFail(String error) {

    }

}
