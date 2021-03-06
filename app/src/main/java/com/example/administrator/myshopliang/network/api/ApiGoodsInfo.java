package com.example.administrator.myshopliang.network.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.myshopliang.network.core.ApiInterface;
import com.example.administrator.myshopliang.network.core.ApiPath;
import com.example.administrator.myshopliang.network.core.RequestParam;
import com.example.administrator.myshopliang.network.core.ResponseEntity;
import com.example.administrator.myshopliang.network.entity.GoodsInfoReq;
import com.example.administrator.myshopliang.network.entity.GoodsInfoRsp;


/**
 * Created by gqq on 2017/3/3.
 */
// 服务器接口：商品详情的请求
public class ApiGoodsInfo implements ApiInterface {

    // 构建请求体数据
    GoodsInfoReq mGoodsInfoReq;

    public ApiGoodsInfo(int goodsId) {
        mGoodsInfoReq = new GoodsInfoReq();
        mGoodsInfoReq.setGoodsId(goodsId);
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.GOODS_INFO;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return mGoodsInfoReq;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return GoodsInfoRsp.class;
    }
}
