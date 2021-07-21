package com.lxl.mvpwork.adapter;

import android.app.Activity;
import android.app.Application;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.xtoast.OnClickListener;
import com.hjq.xtoast.XToast;
import com.lxl.mvpwork.R;
import com.lxl.mvpwork.bean.GankResponse;

import java.util.List;

/**
 * 列表适配器
 *
 * @author lxl
 */
public class GankListAdapter extends BaseQuickAdapter<GankResponse.DataBean, BaseViewHolder> {


    public GankListAdapter(int layoutResId, @Nullable List<GankResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankResponse.DataBean item) {
        String imgUrl = item.getImages().get(0);

        Glide.with(mContext).load((imgUrl != null && !imgUrl.isEmpty()) ? imgUrl : "").into((ImageView) helper.getView(R.id.image));
        helper.setText(R.id.desc, item.getDesc()).addOnClickListener(R.id.mLine);

    }
}
