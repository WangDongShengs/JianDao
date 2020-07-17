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

import com.wds.adapter.VideoAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.VideoBean;
import com.wds.jiandao.R;
import com.wds.presenter.VideoPresenter;
import com.wds.util.MvpInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseMVPFragment<VideoPresenter, MvpInterface.VideoView> implements MvpInterface.VideoView {


    @BindView(R.id.tv_cl_video)
    TextView tvClVideo;
    @BindView(R.id.rl_cl_video)
    RecyclerView rlClVideo;
    private VideoAdapter videoAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected MvpInterface.VideoView initMVPView() {
        return this;
    }

    @Override
    protected void initView() {
        super.initView();
        rlClVideo.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rlClVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoAdapter = new VideoAdapter(getActivity());
        rlClVideo.setAdapter(videoAdapter);
    }

    @Override
    protected VideoPresenter initMVPPresenter() {
        return new VideoPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.VideoPresenter();
    }

    @Override
    public void onSuccess(VideoBean videoBean) {
    videoAdapter.setListBeans(videoBean.getData().getList());
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
