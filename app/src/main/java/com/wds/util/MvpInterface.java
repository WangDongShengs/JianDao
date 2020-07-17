package com.wds.util;

import com.wds.base.BaseCallBack;
import com.wds.base.BaseView;
import com.wds.bean.HomeBean;
import com.wds.bean.HomeTabBean;
import com.wds.bean.SpecialBean;
import com.wds.bean.VideoBean;

public interface MvpInterface {
    //首頁
    interface HomeView extends BaseView<HomeBean>{
        @Override
        void onSuccess(HomeBean homeBean);

        @Override
        void onFail(String error);
    }
  interface HomeCallBack extends BaseCallBack<HomeBean>{
      @Override
      void onSuccess(HomeBean homeBean);

      @Override
      void onFail(String error);
  }
  //首頁Tab
  interface HomeTabView extends BaseView<HomeTabBean>{
        @Override
        void onSuccess(HomeTabBean homeTabBean);

        @Override
        void onFail(String error);
    }
  interface HomeTabCallBack extends BaseCallBack<HomeTabBean>{
      @Override
      void onSuccess(HomeTabBean homeTabBean);

      @Override
      void onFail(String error);
  }
  //視頻
    interface VideoView extends BaseView<VideoBean>{

    @Override
    void onSuccess(VideoBean videoBean);

    @Override
    void onFail(String error);
}
interface VideoCallBack extends BaseCallBack<VideoBean>{
    @Override
    void onSuccess(VideoBean videoBean);

    @Override
    void onFail(String error);
}
//专题
interface SpecialView extends BaseView<SpecialBean>{

    @Override
    void onSuccess(SpecialBean specialBean);
    @Override
    void onFail(String error);

}
interface SpecialCallBack extends BaseView<SpecialBean>{

    @Override
    void onSuccess(SpecialBean specialBean);
    @Override
    void onFail(String error);

}
}
