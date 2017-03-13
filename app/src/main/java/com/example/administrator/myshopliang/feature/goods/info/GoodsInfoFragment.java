package com.example.administrator.myshopliang.feature.goods.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.myshopliang.R;
import com.example.administrator.myshopliang.base.utils.BaseActivity;
import com.example.administrator.myshopliang.base.utils.BaseFragment;
import com.example.administrator.myshopliang.feature.goods.GoodsActivity;
import com.example.administrator.myshopliang.network.core.ResponseEntity;
import com.example.administrator.myshopliang.network.entity.GoodsInfo;
import com.example.administrator.myshopliang.network.entity.Picture;
import com.example.administrator.myshopliang.widgets.banner.BannerAdapter;
import com.example.administrator.myshopliang.wrapper.ToastWrapper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2017/3/12.
 */
//商品详情
public class GoodsInfoFragment extends BaseFragment{
    @BindView(R.id.pager_goods_pictures)
    ViewPager mPicturePager;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.button_favorite)
    ImageButton mBtnFavorite;//收藏
    @BindView(R.id.text_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.text_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.text_market_price)
    TextView mTvmarketPrice;

    private static final String ARGUMENT_GOODS_INFO = "goods_info";
    private static GoodsInfoFragment goodsInfoFragment;
    private GoodsInfo goodsInfo;

    // 提供一个创建方法：传递数据
    public static GoodsInfoFragment newInstance(@NonNull GoodsInfo goodsInfo){
        goodsInfoFragment = new GoodsInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_GOODS_INFO,new Gson().toJson(goodsInfo));
        goodsInfoFragment.setArguments(bundle);
        return goodsInfoFragment;
    }


    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_goods_info;
    }

    @Override
    protected void initView() {
        //视图的初始化工作
        //拿到传递过来的数据
        String string=getArguments().getString(ARGUMENT_GOODS_INFO);
        goodsInfo = new Gson().fromJson(string, GoodsInfo.class);
        //Viewpager
        BannerAdapter<Picture> adapter=new BannerAdapter<Picture>() {
            @Override
            protected void bind(ViewHolder holder, Picture data) {
                Picasso.with(getContext()).load(data.getLarge()).into(holder.mImageView);
            }
        };
        // 适配器数据
        adapter.reset(goodsInfo.getPictures());
        // 设置适配器并且为圆点指示器设置ViewPager
        mPicturePager.setAdapter(adapter);
        mIndicator.setViewPager(mPicturePager);  //设置圆点指示器
        adapter.registerDataSetObserver(mIndicator.getDataSetObserver()); //适配器设置动态？？？？？？？？？

        // 商品的名称
        mTvGoodsName.setText(goodsInfo.getName());
        // 商品价格
        mTvGoodsPrice.setText(goodsInfo.getShopPrice());
        // 商品的商场价格
        String marketPrice = goodsInfo.getMarketPrice();
        SpannableString spannableString = new SpannableString(marketPrice);//带横线那个价格
        // 设置删除线：横线
        spannableString.setSpan(new StrikethroughSpan(),0,marketPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvmarketPrice.setText(spannableString);
        mBtnFavorite.setSelected(goodsInfo.isCollected());//收藏
    }

    // 商品评价
    @OnClick(R.id.text_goods_comments)
    public void changeToConmment(){
        // 切换到商品评论的Fragment
      GoodsActivity activity = (GoodsActivity) getActivity();
        activity.selectPage(2);
    }

    // 商品详情
    @OnClick(R.id.text_goods_details)
    public void changeToDetail(){
        // 切换到商品详情的Fragment
        GoodsActivity activity = (GoodsActivity) getActivity();
        activity.selectPage(1);
    }

    // 收藏商品
    @OnClick(R.id.button_favorite)
    public void coolectGoods(){
        ToastWrapper.show(goodsInfo.getName());
    }
    @Override
    protected void onBusinessResponse(String path, boolean isSucces, ResponseEntity responseEntity) {

    }

}



