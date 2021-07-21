package com.lxl.mvpwork.contract;

import android.annotation.SuppressLint;

import com.lxl.mvpwork.api.ApiService;
import com.lxl.mvpwork.bean.GankResponse;
import com.lxl.mvplibrary.base.BasePresenter;
import com.lxl.mvplibrary.base.BaseView;
import com.lxl.mvplibrary.network.NetworkApi;
import com.lxl.mvplibrary.network.observer.BaseObserver;

/**
 * 将V与M订阅起来
 * @author
 */
public class MainContract {

    public static class MainPresenter extends BasePresenter<IMainView> {

        @SuppressLint("CheckResult")
        public void getGankList(){
            ApiService service  = NetworkApi.createService(ApiService.class);
            service.getList().compose(NetworkApi.applySchedulers(new BaseObserver<GankResponse>() {
                @Override
                public void onSuccess(GankResponse gankResponse) {
                    if (getView() != null) {
                        getView().getListResult(gankResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getListFailed(e);
                    }
                }
            }));
        }
    }

    public interface IMainView extends BaseView {
        //返回列表结果
        void getListResult(GankResponse gankResponse);
        //获取列表失败返回
        void getListFailed(Throwable e);
    }
}
