package com.lxl.mvpwork.application;

import com.lxl.mvplibrary.BaseApplication;
import com.lxl.mvplibrary.network.NetworkApi;


/**
 * 自定义Application
 * @author lxl
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        NetworkApi.init(new NetworkRequiredInfo(this));
    }
}
