package com.example.administrator.myshopliang.network.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.myshopliang.network.core.ApiInterface;
import com.example.administrator.myshopliang.network.core.ApiPath;
import com.example.administrator.myshopliang.network.core.RequestParam;
import com.example.administrator.myshopliang.network.core.ResponseEntity;
import com.example.administrator.myshopliang.network.entity.CategoryRsp;


/**
 * Created by gqq on 2017/3/3.
 */

// 服务器接口：获取商品的分类信息
public class ApiCategory implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.CATEGORY;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return null;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return CategoryRsp.class;
    }
}
