package com.wds.jiandao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wds.adapter.MainPagerAdapter;
import com.wds.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends BaseActivity {

    @BindView(R.id.cl_vp)
    ViewPager clVp;

    @Override
    protected int getLayout() {
        return R.layout.activity_pager;
    }

    @Override
    public void initView() {
        super.initView();
        List<Integer> integers = new ArrayList<>();
        integers.add(R.mipmap.splash_guild1);
        integers.add(R.mipmap.splash_guild2);
        integers.add(R.mipmap.splash_guild3);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(this, integers);
        clVp.setAdapter(mainPagerAdapter);
        mainPagerAdapter.setClickItem(new MainPagerAdapter.onClickItem() {
            @Override
            public void item(int i) {
                Intent intent = new Intent(PagerActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
