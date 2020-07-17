package com.wds.common;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class UserApplication extends Application {
    public static UserApplication userApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        userApplication=this;
       // 创建app签名
        initUmeng();
    }

    private void initUmeng() {

/*        //友盟的key和value
        //签名：aabdc775e668794f406883f14f208c5a
        //appkey:5edd92e3978eea085d11d57f
//QQ的key和value
        //APP ID：1110507739
        //APP KEY：xl7pfkFZAGxAcq61
//微博的key和value
        //App Key：2792486505
        //App Secret：ef26a058835189a009f92564a6572a50
        UMConfigure.init(this, "5edd92e7dbc2ec083df18f3c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3366343569", "3f2910f5fc9234fe8a590ea8dc844744", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1110507527", "Z1uMEq5IDqKKsDQr");*/



        //友盟key
        //5f0d9dbedbc2ec0841e9cb5e
        //MD5签名
        //78bf69b85ea58b7e29c95a9e6041127e

        //App Key：1313018749
        //App Secret：97d3841273f650c652bfa72a7ff885bf
        UMConfigure.init(this,"5f0d9dbedbc2ec0841e9cb5e","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //服务器端配置
        PlatformConfig.setSinaWeibo("1313018749", "97d3841273f650c652bfa72a7ff885bf","https://api.weibo.com/oauth2/default.html");
        //PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("1110507527", "Z1uMEq5IDqKKsDQr");
        //PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        //PlatformConfig.setAlipay("2015111700822536");
        //PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        //PlatformConfig.setPinterest("1439206");
        //PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        //PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        //PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        //PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");*/
        // PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");

    }

    public static UserApplication getUserApplication() {
        return userApplication;
    }


}
