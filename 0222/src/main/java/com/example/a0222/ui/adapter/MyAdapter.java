package com.example.a0222.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a0222.R;
import com.example.a0222.data.bean.Sean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SubViewHolder> {

    Context mContext;

    private List<Sean.ResultsBean> beanList;


    public MyAdapter(Context mContext, List<Sean.ResultsBean> list) {
        this.mContext = mContext;
        beanList = new ArrayList<>();
    }


    public void setdata(List<Sean.ResultsBean> data) {
        beanList.clear();
        beanList.addAll(data);
        notifyDataSetChanged();
    }

    public void adddata(List<Sean.ResultsBean> data) {
        beanList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(mContext, R.layout.item_one_layout, null);
        SubViewHolder subViewHolder = new SubViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {

        subViewHolder.tvOne.setText(beanList.get(i).getType());

        String url = beanList.get(i).getUrl();
        Uri parse = Uri.parse(url);
        subViewHolder.imgOne.setImageURI(parse);
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_one)
        SimpleDraweeView imgOne;
        @BindView(R.id.tv_one)
        TextView tvOne;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
