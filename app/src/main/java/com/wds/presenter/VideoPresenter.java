package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.HomeTabBean;
import com.wds.bean.VideoBean;
import com.wds.model.HomeTabModel;
import com.wds.model.VideoModel;
import com.wds.util.MvpInterface;

public class VideoPresenter extends BasePresenter<MvpInterface.VideoView> implements MvpInterface.VideoCallBack {


    private VideoModel videoModel;

    public void VideoPresenter(){
        videoModel.videoModel(this);
    }
    @Override
    protected void initModel() {
        videoModel = new VideoModel();
        addModel(videoModel);
    }


    @Override
    public void onSuccess(VideoBean videoBean) {
        view.onSuccess(videoBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
