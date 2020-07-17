package com.wds.model;
import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.HomeBean;
import com.wds.net.HttpManager;
import com.wds.util.CommonParameterUtil;
import com.wds.util.MvpInterface;
import com.wds.util.RxUtil;
import java.util.HashMap;

public class HomeModel extends BaseModel {
    public void homeModel(final MvpInterface.HomeCallBack homeCallBack,String id){
        HashMap<String, String> parameMap = new HashMap<>();
        //公共参数
        parameMap.putAll(CommonParameterUtil.getCommonParameter());
        //自己的参数
        parameMap.put("id", id);
        parameMap.put("start", "0");
        parameMap.put("number", "0");
        parameMap.put("point_time", "0");
        HttpManager.getInstance().getApiService(ApiService.baseUrl,ApiService.class)
                .getRecommendedData(parameMap)
                .compose(RxUtil.<HomeBean>rxFlowableTransformer())
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onSuccess(HomeBean homeBean) {
                        homeCallBack.onSuccess(homeBean);
                    }
                });

    }
}
