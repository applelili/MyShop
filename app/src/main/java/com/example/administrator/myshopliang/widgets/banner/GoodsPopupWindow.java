package com.example.administrator.myshopliang.widgets.banner;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.myshopliang.R;
import com.example.administrator.myshopliang.base.utils.BaseActivity;
import com.example.administrator.myshopliang.network.entity.GoodsInfo;
import com.example.administrator.myshopliang.wrapper.ToastWrapper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/11.
 */

public class GoodsPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener {

    private final ViewGroup parents;
    private GoodsInfo mGoodsInfo;
    private BaseActivity mActivity;
    @BindView(R.id.image_goods)
    ImageView mIvGoods;
    @BindView(R.id.text_goods_price)
    TextView mTvPrice;
    @BindView(R.id.text_number_value)
    TextView mTvNumber;
    @BindView(R.id.text_inventory_value)
    TextView mTvInventory;
    @BindView(R.id.number_picker)
    SimpleNumberPicker mNumberPicker;
    private OnConfirmListener mOnConfirmListener;

    /**
     * 1. 布局填充：构造方法里面
     * 2. 显示：show方法，弹出展示
     * 3. 弹出和隐藏的时候设置背景的透明度
     * 4. 商品数据的展示：需要数据，所以可以在构造方法中传递数据
     */

    public GoodsPopupWindow(BaseActivity activity, @NonNull GoodsInfo goodsInfo) {//构造方法设置背景颜色时需要activity，因为上下文也可以在activity中拿到，所以改成BaseActivity，还有数据传递

        this.mActivity = activity;
        this.mGoodsInfo = goodsInfo;
        //布局填充
        //最上层的view
        parents = (ViewGroup) activity.getWindow().getDecorView();
        //拿到上下文
        Context context = parents.getContext();
        //拿到布局
        View view = LayoutInflater.from(activity).inflate(R.layout.popup_goods_spec, parents, false);
        //设置布局
        setContentView(view);

        //设置宽高

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(context.getResources().getDimensionPixelSize(R.dimen.size_400));
        //设置背景
        setBackgroundDrawable(new ColorDrawable());
        // 设置点击窗体外部popupWindow消失
        setFocusable(true);
        setOutsideTouchable(true);
        //有软键盘:调整大小，留出软键盘的空间
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        // 效果：弹出和隐藏的时候设置窗体背景的透明度

        setOnDismissListener(this);

        ButterKnife.bind(this, view);

        //视图的填充
        initView();
    }

    //商品数据的展示
    private void initView() {//对popuwindow中所有的数据的绑定
        //商品的价格，库存，图片
        Picasso.with(parents.getContext()).load(mGoodsInfo.getImg().getLarge()).into(mIvGoods);//找到上下文传来的数据图片地址，并传给mIvGOODS图片
        mTvPrice.setText(mGoodsInfo.getShopPrice());
        mTvInventory.setText(String.valueOf(mGoodsInfo.getNumber()));
        //商品数量选择器

        mNumberPicker.setOnNumberChangeListener(new SimpleNumberPicker.OnNumberChangeListener() {
            @Override//可以拿到商品数量选择的商品数量
            public void onNumberChanged(int number) {
                mTvNumber.setText(String.valueOf(number));
            }
        });
    }

    // 确认按钮的点击事件
    @OnClick(R.id.button_ok)
    public void onClick(View view) {
        // 具体选择了多少个商品给使用者传递出去，具体的事件我们并不知道：跳转到购买页面还是直接只加入购物车
        // 所以我们把具体的事件实现交给使用者去处理
        int number=mNumberPicker.getNumber();
        if(number==0){//商品为0不做操作
            ToastWrapper.show(R.string.goods_msg_must_choose_number);
            return;
        }
        mOnConfirmListener.onConfirm(number);
    }


    // // 展示PopupWindow:参数中实现接口的监听
    public void show(OnConfirmListener mOnConfirmListener) {
        this.mOnConfirmListener=mOnConfirmListener;
        //从哪里显示出来
        showAtLocation(parents, Gravity.BOTTOM, 0, 0);
        // 设置背景透明度
        backgroundAlpha(0.5f);
    }

    // 弹窗消失的时候会调用
    @Override
    public void onDismiss() {
        backgroundAlpha(1.0f);
    }

    //改变透明度的方法：根据传入的透明度来变化:0.0f-1.0f
    private void backgroundAlpha(float bgAlpha) {
        //改变的窗体的属性变化
        WindowManager.LayoutParams layoutParams = mActivity.getWindow().getAttributes();
        layoutParams.alpha = bgAlpha;
        mActivity.getWindow().setAttributes(layoutParams);
    }

    //确认监听
    public interface OnConfirmListener {
        void onConfirm(int number);
    }
}
