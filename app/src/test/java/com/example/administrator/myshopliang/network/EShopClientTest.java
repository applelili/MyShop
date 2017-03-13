package com.example.administrator.myshopliang.network;

import com.example.administrator.myshopliang.network.api.ApiCategory;
import com.example.administrator.myshopliang.network.api.ApiGoodsInfo;
import com.example.administrator.myshopliang.network.api.ApiHomeBanner;
import com.example.administrator.myshopliang.network.api.ApiHomeCategory;
import com.example.administrator.myshopliang.network.api.ApiSearch;
import com.example.administrator.myshopliang.network.entity.CategoryRsp;
import com.example.administrator.myshopliang.network.entity.GoodsInfoRsp;
import com.example.administrator.myshopliang.network.entity.HomeBannerRsp;
import com.example.administrator.myshopliang.network.entity.HomeCategoryRsp;
import com.example.administrator.myshopliang.network.entity.SearchReq;
import com.example.administrator.myshopliang.network.entity.SearchRsp;
import com.google.gson.Gson;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/2/24.
 */
public class EShopClientTest {
    //搜索页面
    @Test
    public void getSearch() throws Exception {

   /*     SearchReq searchReq = new SearchReq();
        Call call = EShopClient.getInstance().getSearch(searchReq);
        Response response = call.execute();
        String json = response.body().string();
        SearchRsp searchRsp = new Gson().fromJson(json, SearchRsp.class);*/
        ApiSearch apiSearch = new ApiSearch(null,null);
        SearchRsp searchRsp=EShopClient.getInstance().execute(apiSearch);
        // 断言方法：为我们做一个判断
        assertTrue(searchRsp.getStatus().isSucceed());
    }
    // 首页：banner
    @Test
    public void getHomeBanner() throws Exception {

        HomeBannerRsp homeBannerRsp=EShopClient.getInstance().execute(new ApiHomeBanner());

        assertTrue(homeBannerRsp.getStatus().isSucceed());
    }
//首页：分类和推荐商品
    @Test
    public void getHomeCategory() throws Exception {

       HomeCategoryRsp categoryRsp=EShopClient.getInstance().execute(new ApiHomeCategory());
        assertTrue(categoryRsp.getStatus().isSucceed());
    }
    // 分类页面
    @Test
    public void getCategory() throws Exception {
        CategoryRsp categoryRsp = EShopClient.getInstance().execute(new ApiCategory());
        assertTrue(categoryRsp.getStatus().isSucceed());
    }
    // 商品详情的请求
    @Test
    public void getGoodsInfo() throws Exception{
        ApiGoodsInfo apiGoodsInfo = new ApiGoodsInfo(78);
        GoodsInfoRsp goodsInfoRsp = EShopClient.getInstance().execute(apiGoodsInfo);
        assertTrue(goodsInfoRsp.getStatus().isSucceed());
    }
}













