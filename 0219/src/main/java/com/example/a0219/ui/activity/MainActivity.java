package com.example.a0219.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.a0219.R;
import com.example.a0219.data.bean.BannerBean;
import com.example.a0219.data.bean.GoodsBean;
import com.example.a0219.data.utils.Apis;
import com.example.a0219.di.presenter.IPresenter;
import com.example.a0219.di.presenter.ShowPresenter;
import com.example.a0219.di.view.IView;
import com.example.a0219.ui.adapter.MyAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private ShowPresenter showPresenter;
    private MyAdapter myAdapter;


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
        showPresenter = new ShowPresenter(this);

    }

    //轮播图
    private void initBanner() {
        showPresenter.requestData(Apis.BANNER_URL, BannerBean.class);
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
    }

    /*
     *   商品数据
     * */
        private void initData () {
            showPresenter.requestData(Apis.DATASHOW_URL,GoodsBean.class);

            myAdapter = new MyAdapter(this);
            recycleView.setAdapter(myAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            recycleView.setLayoutManager(linearLayoutManager);

            myAdapter.setClickListener(new MyAdapter.ClickListener() {
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
        public void OnSuccess (Object data){

            if(data instanceof BannerBean){
                BannerBean bannerBean = (BannerBean) data;
                List<String> list = new ArrayList<>();
                for (int i = 0; i<bannerBean.result.size();i++){
                    list.add(bannerBean.result.get(i).getImageUrl());
                }
                banner.setImages(list);
                banner.start();
            }else if(data instanceof GoodsBean){
                GoodsBean goodsBean = (GoodsBean) data;
                myAdapter.setDatas(goodsBean.getResult().getMlss().get(0).getCommodityList());
            }

        }


        //内存泄漏
        @Override
        protected void onDestroy () {
            super.onDestroy();
            showPresenter.onDetach();
        }

}
