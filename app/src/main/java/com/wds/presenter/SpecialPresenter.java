package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.SpecialBean;
import com.wds.bean.VideoBean;
import com.wds.model.SpecialModel;
import com.wds.model.VideoModel;
import com.wds.util.MvpInterface;

public class SpecialPresenter extends BasePresenter<MvpInterface.SpecialView> implements MvpInterface.SpecialCallBack {


    private SpecialModel specialModel;

    public void SpecialPresenter(){
        specialModel.specialModel(this);
    }
    @Override
    protected void initModel() {
        specialModel = new SpecialModel();
        addModel(specialModel);
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        view.onSuccess(specialBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
