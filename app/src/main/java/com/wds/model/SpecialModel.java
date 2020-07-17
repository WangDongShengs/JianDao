package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.SpecialBean;
import com.wds.bean.VideoBean;
import com.wds.net.HttpManager;
import com.wds.util.CommonParameterUtil;
import com.wds.util.MvpInterface;
import com.wds.util.RxUtil;

import java.util.HashMap;

public class SpecialModel extends BaseModel {
    public void specialModel(final MvpInterface.SpecialCallBack specialCallBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.putAll(CommonParameterUtil.getCommonParameter());
        hashMap.put("start","0");
        hashMap.put("number","0");
        hashMap.put("point_time","0");
        hashMap.put("token","");
        HttpManager.getInstance().getApiService(ApiService.baseUrl,ApiService.class)
               .getSpeciallist(hashMap).compose(RxUtil.<SpecialBean>rxFlowableTransformer())
                .subscribe(new BaseObserver<SpecialBean>() {
                    @Override
                    public void onSuccess(SpecialBean specialBean) {
                        specialCallBack.onSuccess(specialBean);
                    }
                });

    }
    }
