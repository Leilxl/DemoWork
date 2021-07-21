package com.lxl.mvpwork;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxl.mvpwork.adapter.GankListAdapter;
import com.lxl.mvpwork.bean.GankResponse;
import com.lxl.mvpwork.contract.MainContract;
import com.lxl.mvplibrary.mvp.MvpActivity;
import com.lxl.mvplibrary.network.utils.KLog;
import com.lxl.mvpwork.view.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */
public class MainActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView {

    private RecyclerView rv;

    private static final String TAG = "MainActivity";

    private List<GankResponse.DataBean> mList = new ArrayList<>();
    private GankListAdapter mAdapter;
    private ToastUtil toast;

    @Override
    public void initData(Bundle savedInstanceState) {
        toast = new ToastUtil();
        //显示加载弹窗
        showLoadingDialog();
        //初始化列表
        initList();

    }

    /**
     * 初始化列表
     */
    private void initList() {
        rv = findViewById(R.id.rv);
        //配置rv
        mAdapter = new GankListAdapter(R.layout.item_list, mList);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);

        //请求列表数据
        mPresenter.getGankList();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                toast.showToast(MainActivity.this,"我点击了第" + position + "条目",2);

            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }

    /**
     * 获取列表数据返回
     *
     * @param gankResponse
     */
    @Override
    public void getListResult(GankResponse gankResponse) {
        if (gankResponse.getData() != null && gankResponse.getData().size() > 0) {
            mList.clear();
            mList.addAll(gankResponse.getData());
            mAdapter.notifyDataSetChanged();
            hideLoadingDialog();
        } else {
            showMsg("数据为空");
            hideLoadingDialog();
        }
    }

    /**
     * 获取列表数据异常
     *
     * @param e
     */
    @Override
    public void getListFailed(Throwable e) {
        KLog.e(TAG, e.toString());
        showMsg("获取列表数据异常，具体日志信息请查看日志");
        hideLoadingDialog();
    }


}
