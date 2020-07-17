package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.HomeBean;
import com.wds.model.HomeModel;
import com.wds.util.MvpInterface;

public class HomePresenter extends BasePresenter<MvpInterface.HomeView> implements MvpInterface.HomeCallBack {

    private HomeModel homeModel;
    public void HomePresenter(String  id){
        homeModel.homeModel(this,id);
    }
    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        addModel(homeModel);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        view.onSuccess(homeBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
