package com.example.administrator.myshopliang.feature.goods;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myshopliang.base.utils.TestFragment;
import com.example.administrator.myshopliang.feature.goods.info.GoodsInfoFragment;
import com.example.administrator.myshopliang.network.entity.GoodsInfo;

/**
 * Created by gqq on 2017/3/6.
 */

// 商品页面的适配器
public class GoodsPagerAdapter extends FragmentPagerAdapter {

    // 数据的传递
    private GoodsInfo mGoodsInfo;

    public GoodsPagerAdapter(FragmentManager fm, GoodsInfo goodsInfo) {
        super(fm);
        this.mGoodsInfo = goodsInfo;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
              //  return GoodsInfoFragment.newInstance(mGoodsInfo);
                return GoodsInfoFragment.newInstance(mGoodsInfo);
            case 1:
                return TestFragment.newInstance("goods details");
            case 2:
                return TestFragment.newInstance("goods comments");
            default:
                throw new UnsupportedOperationException("Position" + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
