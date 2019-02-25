package com.example.a0220.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.a0220.R;
import com.example.a0220.data.bean.Good;
import com.example.a0220.data.utils.Apis;
import com.example.a0220.di.presenter.IPresenterImpl;
import com.example.a0220.di.view.IView;
import com.example.a0220.ui.adapter.GoodsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {


    @BindView(R.id.hand)
    RecyclerView hand;
    private IPresenterImpl iPresenter;
    private GoodsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    /*
     *   初始化View
     * */
    private void initView() {
        iPresenter = new IPresenterImpl(this);


    }


    /*
     *   商品数据
     * */
    public void initData() {
        iPresenter.pRequestData(Apis.DATASHOW_URL, Good.class);

        mAdapter = new GoodsAdapter(this);
        hand.setAdapter(mAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        hand.setLayoutManager(linearLayoutManager);

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


        if (data instanceof Good) {
            Good goodsBean = (Good) data;
            mAdapter.setDatas(goodsBean.getNewslist());
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
