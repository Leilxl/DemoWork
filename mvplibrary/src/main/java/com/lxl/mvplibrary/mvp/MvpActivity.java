package com.lxl.mvplibrary.mvp;

import android.os.Bundle;
import android.widget.Toast;

import com.lxl.mvplibrary.base.BaseActivity;
import com.lxl.mvplibrary.base.BasePresenter;
import com.lxl.mvplibrary.base.BaseView;

/**
 * 适用于需要访问网络接口的Activity
 *
 * @author lxl
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();


    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        //创建
        mPresenter = createPresenter();
        //绑定View
        mPresenter.attachView((BaseView) this);
    }

    /**
     * 页面销毁时解除绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
