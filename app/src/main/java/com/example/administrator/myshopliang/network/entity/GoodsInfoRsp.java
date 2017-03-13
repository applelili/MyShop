package com.example.administrator.myshopliang.network.entity;

import com.example.administrator.myshopliang.network.core.ResponseEntity;
import com.google.gson.annotations.SerializedName;

// 商品详情的响应体
public class GoodsInfoRsp extends ResponseEntity {

    @SerializedName("data")
    private GoodsInfo mData;

    public GoodsInfo getData() {
        return mData;
    }

}
