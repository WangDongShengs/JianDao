package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.HomeBean;
import com.wds.bean.HomeTabBean;
import com.wds.model.HomeModel;
import com.wds.model.HomeTabModel;
import com.wds.util.MvpInterface;

public class HomeTabPresenter extends BasePresenter<MvpInterface.HomeTabView> implements MvpInterface.HomeTabCallBack {

    private HomeTabModel homeTabModel;

    public void HomeTabPresenter(){
       homeTabModel.homeTabModel(this);
    }
    @Override
    protected void initModel() {
        homeTabModel = new HomeTabModel();
        addModel(homeTabModel);
    }


    @Override
    public void onSuccess(HomeTabBean homeTabBean) {
        view.onSuccess(homeTabBean);
    }

    @Override
    public void onFail(String error) {
            view.onFail(error);
    }
}
