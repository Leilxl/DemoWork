package com.lxl.mvplibrary.mvp;

import android.os.Bundle;

import com.lxl.mvplibrary.base.BaseFragment;
import com.lxl.mvplibrary.base.BasePresenter;
import com.lxl.mvplibrary.base.BaseView;

/**
 * 适用于需要访问网络接口的Fragment
 *
 * @author lxl
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mPresenter;

    /**
     * 创建Presenter
     */
    protected abstract P createPresent();

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        mPresenter = createPresent();
        mPresenter.attachView((BaseView) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
