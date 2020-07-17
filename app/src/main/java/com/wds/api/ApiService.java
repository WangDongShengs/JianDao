package com.wds.api;

import android.database.Observable;

import com.wds.bean.HomeBean;
import com.wds.bean.HomeTabBean;
import com.wds.bean.SpecialBean;
import com.wds.bean.VideoBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {
    String baseUrl = "https://www.seetao.com/";

    /**
     * 推荐
     * @param map
     * @return
     */
    //首页
    @GET("/app/v_1_3/article/recommendlist")
    Flowable<HomeBean> getRecommendedData(@QueryMap Map<String, String> map);
    //首页Tab
    @GET("api/column/columnlist")
    Flowable<HomeTabBean> getColumnList(@QueryMap Map<String, String> map);

    //视频
    @GET("app/v_1_3/article/videolist")
    Flowable<VideoBean> getVideoList(@QueryMap Map<String, String> map);

    //专题
    @GET("app/v_1_3/article/speciallist")
    Flowable<SpecialBean> getSpeciallist(@QueryMap Map<String, String> map);

    @GET("app/v_1_3/ad/coopen")
    Observable<ResponseBody> getData1(@QueryMap Map<String, String> map);
    @POST("api/user/register")
    @FormUrlEncoded
    Observable<ResponseBody> getReg(@FieldMap Map<String, String> parmap) ;
}
