package com.example.a0220.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.Toast;

import com.example.a0220.R;
import com.example.a0220.data.bean.Good;
import com.example.a0220.data.utils.Apis;
import com.example.a0220.di.presenter.IPresenterImpl;
import com.example.a0220.di.view.IView;
import com.example.a0220.ui.adapter.GoodsAdapter;
import com.example.a0220.ui.adapter.MyAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity implements IView {


    private MyAdapter mAdapter;
    private Good good;
    private int mPage ;
    private List<Good.NewslistBean> list;
    @BindView(R.id.xrecycler)
    XRecyclerView xrecycler;
    private IPresenterImpl iPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        iPresenter = new IPresenterImpl(this);
        init();
    }

    private void init() {

        iPresenter = new IPresenterImpl(this);
        //管理器不变
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         xrecycler.setLayoutManager(layoutManager);


        mAdapter = new MyAdapter(list,this);
        xrecycler.setAdapter(mAdapter);


        mAdapter.setClickListener(new MyAdapter.ClickListener() {
            @Override
            public void OnClick(int i, String name) {

                Toast.makeText(SecondActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        xrecycler.setPullRefreshEnabled(true);
        xrecycler.setLoadingMoreEnabled(true);

        xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                initDate();
            }

            @Override
            public void onLoadMore() {

                // 数据增加  刷新适配器
                 mPage++;
                initDate();
            }
        });

        initDate();
       

    }
    /*initDate()方法中不要去写   mAdapter = new MyAdapter(list,this);
                             xrecycler.setAdapter(mAdapter);*/

   private void initDate() {

        iPresenter.pRequestData(Apis.DATASHOW_URL, Good.class);

    }


    @Override
    public void onSuccess(Object data) {

     good = (Good) data;
        if(mPage == 1){
            mAdapter.setdata(good.getNewslist());
        }else{
            mAdapter.adddata(good.getNewslist());
        }
        mPage++;

        xrecycler.refreshComplete();
        xrecycler.loadMoreComplete();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetch();
    }
}
