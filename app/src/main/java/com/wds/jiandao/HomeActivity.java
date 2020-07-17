package com.wds.jiandao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wds.base.BaseActivity;
import com.wds.custom.NoSlideViewPager;
import com.wds.fragment.HomeFragment;
import com.wds.fragment.MeFragment;
import com.wds.fragment.SpecialFragment;
import com.wds.fragment.VideoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.cl_pager)
    ViewPager clPager;
    @BindView(R.id.cl_tab)
    TabLayout clTab;


    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new VideoFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new MeFragment());
        clPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        clTab.setupWithViewPager(clPager);
        final String[] name = {"推荐", "视频", "专题", "我"};
        final int[] pic = {R.drawable.ic_tab_vector_recommend_select, R.drawable.ic_tab_vector_video_select, R.drawable.ic_tab_vector_special_select, R.drawable.ic_tab_vector_mine_select};
        final int[] pic1 = {R.drawable.ic_tab_vector_recommend_normal, R.drawable.ic_tab_vector_video_normal, R.drawable.ic_tab_vector_special_normal, R.drawable.ic_tab_vector_mine_norma};

        clTab.getTabAt(0).setText(name[0]).setIcon(pic[0]);
        clTab.getTabAt(1).setText(name[1]).setIcon(pic1[1]);
        clTab.getTabAt(2).setText(name[2]).setIcon(pic1[2]);
        clTab.getTabAt(3).setText(name[3]).setIcon(pic1[3]);
        clTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //禁止table layout效果
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                clTab.getTabAt(tab.getPosition()).setIcon(pic[tab.getPosition()]);
            }

            //点击table layout效果
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                clTab.getTabAt(tab.getPosition()).setIcon(pic1[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
