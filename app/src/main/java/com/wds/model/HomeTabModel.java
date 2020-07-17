package com.wds.model;

import android.util.Log;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.HomeBean;
import com.wds.bean.HomeTabBean;
import com.wds.net.HttpManager;
import com.wds.util.CommonParameterUtil;
import com.wds.util.MvpInterface;
import com.wds.util.RxUtil;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeTabModel extends BaseModel {
    public void homeTabModel(final MvpInterface.HomeTabCallBack homeTabCallBack) {
        HashMap<String, String> parameMap = new HashMap<>();
        //公共参数
        parameMap.putAll(CommonParameterUtil.getCommonParameter());
        //自己的参数
        parameMap.put("token", "");
        HttpManager.getInstance().getApiService(ApiService.baseUrl, ApiService.class)
                .getColumnList(parameMap).compose(RxUtil.<HomeTabBean>rxFlowableTransformer())
                .subscribe(new BaseObserver<HomeTabBean>() {
                    @Override
                    public void onSuccess(HomeTabBean homeTabBean) {
                        homeTabCallBack.onSuccess(homeTabBean);
                    }
                });

    }
    }
