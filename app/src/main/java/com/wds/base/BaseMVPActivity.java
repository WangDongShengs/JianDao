package com.wds.base;

public abstract class BaseMVPActivity <P extends BasePresenter,V extends BaseView> extends BaseActivity{
    protected  P presenter;

    @Override
    protected void initMVP() {
        super.initMVP();
      presenter=  initMVPPresenter();
      if (presenter!=null){
          presenter.setView(initMVPView());
      }    }

    protected abstract V initMVPView();

    protected abstract P initMVPPresenter();

}
