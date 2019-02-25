package com.example.dell.nl.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.nl.R;
import com.example.dell.nl.data.bean.GoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsBean.ResultBean.RxxpBean.CommodityListBean> mRxxp;
   /* private List<GoodsBean.ResultBean.PzshBean.CommodityListBeanX> mPzss;
    private List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> mMlss;*/
    private Context mContext;

    public GoodsAdapter(Context mContext) {
        this.mContext = mContext;
        mRxxp = new ArrayList<>();
       /*mPzss = new ArrayList<>();
        mMlss = new ArrayList<>();*/
    }

    public void setDatas(List<GoodsBean.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        if (commodityList != null){
            mRxxp.addAll(commodityList);
        }
        notifyDataSetChanged();
    }
   /*public void setDatas1(List<GoodsBean.ResultBean.PzshBean.CommodityListBeanX> commodityList) {
        if (commodityList != null){
            mPzss.addAll(commodityList);
        }
        notifyDataSetChanged();
    }

    public void setDatas2(List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList) {
        if (commodityList != null){
            mMlss.addAll(commodityList);
        }
        notifyDataSetChanged();
    }*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rxxp_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder mHolder = (ViewHolder) viewHolder;
        mHolder.name.setText(mRxxp.get(i).getCommodityName());
      /*  mHolder.name.setText(mPzss.get(i).getCommodityName());
        mHolder.name.setText(mMlss.get(i).getCommodityName());*/

        mHolder.price.setText("￥"+mRxxp.get(i).getPrice());
      /*  mHolder.price.setText("￥"+mPzss.get(i).getPrice());
        mHolder.price.setText("￥"+mMlss.get(i).getPrice());*/

       /* mHolder.imageView.setImageURI(mRxxp.get(i).getMasterPic());*/
        String url = mRxxp.get(i).getMasterPic();
        Uri parse = Uri.parse(url);
        mHolder.imageView.setImageURI(parse);
      /*  mHolder.imageView.setImageURI(mPzss.get(i).getMasterPic());
        mHolder.imageView.setImageURI(mMlss.get(i).getMasterPic());*/


        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null){
                    mClickListener.OnClick(i, mRxxp.get(i).getCommodityName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRxxp.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        public SimpleDraweeView imageView;
        @BindView(R.id.name)
        public TextView name;
        @BindView(R.id.price)
        public TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //ButterKnife.bind(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }
    }

    ClickListener mClickListener;
    public void setClickListener(ClickListener clickListener){
        this.mClickListener = clickListener;
    }
    public interface ClickListener{
        void OnClick(int i, String name);
    }

}
