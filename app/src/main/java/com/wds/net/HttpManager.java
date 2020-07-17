package com.wds.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.wds.common.UserApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static volatile HttpManager httpManager;
    private final Retrofit.Builder builder;

    public static HttpManager getInstance(){
        if (httpManager==null){
            synchronized (HttpManager.class){
                if (httpManager==null){
                    httpManager=new HttpManager();
                }
            }
        }
        return httpManager;
    }
    public HttpManager() {
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(initOkHttpClick())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
    public <T> T getApiService(String baseurl,Class<T> tClass){
        return builder.baseUrl(baseurl).build().create(tClass);
    }
    private OkHttpClient initOkHttpClick() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(new Cache(new File(UserApplication.userApplication.getCacheDir(), Constants.CACHE_NAME), 10 * 1024 * 1024))
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new NetCacheIntercepter())
                .addNetworkInterceptor(new NetCacheIntercepter())
                .retryOnConnectionFailure(true);
        if (Constants.IS_DEBUG){
            builder.addInterceptor(new LoggingInterceptor());
        }
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    /**
     * 网络拦截器
     */
    public class NetCacheIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!isNetworkAvailable(UserApplication.userApplication)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

            }

            Response originalResponse = chain.proceed(request);

            if (isNetworkAvailable(UserApplication.getUserApplication())) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 15 * 60;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }


    /**
     * 日志拦截器
     */
    public class LoggingInterceptor implements Interceptor {
        private static final String TAG = "LoggingInterceptor";

        @Override
        public Response intercept(Chain chain) throws IOException {
            //拦截链对象
            Request request = chain.request();
            Log.d(TAG, String.format("发送请求地址:%s%n请求头:%s", request.url(), request.headers()));
            long startTime = System.currentTimeMillis();
            //递归+循环的方式把所有的拦截器串联起来,并获取响应结果
            Response response = chain.proceed(request);
            long endTime = System.currentTimeMillis();

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);

            Log.d(TAG, String.format("耗时:%s%n收到来自:%s的结果:%n%s", (endTime - startTime) + "ms", response.request().url(), responseBody.string()));

            return response;
        }
    }
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
