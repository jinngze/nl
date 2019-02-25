package com.example.dell.nl.ui.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.dell.nl.R;
import com.example.dell.nl.data.bean.BannerBean;
import com.example.dell.nl.data.bean.GoodsBean;
import com.example.dell.nl.data.utils.Apis;
import com.example.dell.nl.di.presenter.IPresenterImpl;
import com.example.dell.nl.di.view.IView;
import com.example.dell.nl.ui.adapter.GoodsAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements IView {
    private IPresenterImpl iPresenter;
    private Banner banner;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private GoodsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initBanner();
    }
    /*
     *   初始化View
     * */
    private void initView() {
        iPresenter = new IPresenterImpl(this);
        banner = findViewById(R.id.banner);


    }

    /*
    *  Banner轮播图
    * */
    private void initBanner() {
        iPresenter.pRequestData(Apis.BANNER_URL, BannerBean.class);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
       banner.setImageLoader(new ImageLoaderInterface<SimpleDraweeView>() {
            @Override
            public void displayImage(Context context, Object path, SimpleDraweeView imageView) {
                imageView.setImageURI((String) path);
            }

            @Override
            public SimpleDraweeView createImageView(Context context) {
                return new SimpleDraweeView(context);
            }
        });
       /* banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });*/

    }

    /*
    *   商品数据
    * */
    public void initData() {
        iPresenter.pRequestData(Apis.DATASHOW_URL, GoodsBean.class);

        mAdapter = new GoodsAdapter(this);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter.setClickListener(new GoodsAdapter.ClickListener() {
            @Override
            public void OnClick(int i, String name) {
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*
     *   返回的数据
     * */
    @Override
    public void onSuccess(Object data) {

        if (data instanceof BannerBean){
            BannerBean bannerBean = (BannerBean) data;
            List<String> list = new ArrayList<>();
            for (int i = 0; i < bannerBean.result.size(); i++) {
                list.add(bannerBean.result.get(i).getImageUrl());
            }
            banner.setImages(list);
            banner.start();

        }else if (data instanceof GoodsBean){
            GoodsBean goodsBean = (GoodsBean) data;
            mAdapter.setDatas(goodsBean.getResult().getRxxp().get(0).getCommodityList());
           /* mAdapter.setDatas1(goodsBean.getResult().getPzsh().get(0).getCommodityList());
            mAdapter.setDatas2(goodsBean.getResult().getMlss().get(0).getCommodityList());*/

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetch();
    }
}
