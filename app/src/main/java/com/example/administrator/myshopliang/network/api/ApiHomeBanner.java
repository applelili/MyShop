package com.example.administrator.myshopliang.network.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.myshopliang.network.core.ApiInterface;
import com.example.administrator.myshopliang.network.core.ApiPath;
import com.example.administrator.myshopliang.network.core.RequestParam;
import com.example.administrator.myshopliang.network.core.ResponseEntity;
import com.example.administrator.myshopliang.network.entity.HomeBannerRsp;


/**
 * Created by gqq on 2017/3/3.
 */

// 服务器接口：首页的banner轮播图
public class ApiHomeBanner implements ApiInterface {
    @NonNull
    @Override
    public String getPath() {
        return ApiPath.HOME_DATA;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return null;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return HomeBannerRsp.class;
    }
}
