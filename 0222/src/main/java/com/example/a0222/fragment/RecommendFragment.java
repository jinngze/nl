package com.example.a0222.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a0222.R;
import com.example.a0222.data.bean.Sean;
import com.example.a0222.data.utils.Apis;
import com.example.a0222.di.presenter.ShowPresenter;
import com.example.a0222.di.view.IView;
import com.example.a0222.ui.adapter.MyAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecommendFragment extends Fragment implements IView {
    @BindView(R.id.flybanner)
    FlyBanner flybanner;
    @BindView(R.id.hot)
    XRecyclerView hot;
    private FlyBanner flyBanner;
    Unbinder unbinder;
    private MyAdapter myAdapter;
    private List<Sean.ResultsBean> list;
    private ShowPresenter mPresent = new ShowPresenter(this);
    private int mPage ;
    private Sean sean;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.banner, container, false);

        flyBanner = view.findViewById(R.id.flybanner);
        ArrayList<String> list = new ArrayList<>();
        list.add("http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg");
        list.add("http://attach.bbs.miui.com/forum/201303/16/173716jzszx8vbbb0z9o4k.jpg");
        list.add("http://pic28.photophoto.cn/20130929/0034034819144555_b.jpg");
        flyBanner.setImagesUrl(list);

        mPresent = new ShowPresenter(this);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {


    }

    private void initView() {

        //瀑布流
        //2、*布局管理器
        //①线性   LinearLayoutManager
        //②网格   GridLayoutManager
        //③瀑布流 StaggeredGridLayoutManager
        //LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        //GridLayoutManager manager = new GridLayoutManager(context, 2,GridLayoutManager.VERTICAL,false);
        //final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //xRecyclerView.setLayoutManager(manager);
      /*  GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        hot.setLayoutManager(manager);*/
      /*  fresco = new Fresco(mdd,getActivity());
          hot.setAdapter(fresco);*/


        mPresent = new ShowPresenter(this);
        //管理器不变
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hot.setLayoutManager(layoutManager);


        myAdapter = new MyAdapter(getActivity(), list);
        hot.setAdapter(myAdapter);


        hot.setPullRefreshEnabled(true);
        hot.setLoadingMoreEnabled(true);

        hot.setLoadingListener(new XRecyclerView.LoadingListener() {
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

    private void initDate() {

        mPresent.startRequest(Apis.URL_Q,Sean.class);

    }

    @Override
    public void onSuccess(Object data) {


        sean = (Sean) data;
        if(mPage == 1){
            myAdapter.setdata(sean.getResults());
        }else{
            myAdapter.adddata(sean.getResults());
        }
        mPage++;

        hot.refreshComplete();
        hot.loadMoreComplete();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresent.onDetch();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
