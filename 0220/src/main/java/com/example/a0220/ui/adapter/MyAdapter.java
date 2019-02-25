package com.example.a0220.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a0220.R;
import com.example.a0220.data.bean.Good;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SubViewHolder> {


    private List<Good.NewslistBean> mRxxp;

    private Context mContext;



    public MyAdapter(List<Good.NewslistBean> list, Context mContext) {
        this.mContext = mContext;
        mRxxp = new ArrayList<>();
    }

   public void setdata(List<Good.NewslistBean> data) {
        mRxxp.clear();
        mRxxp.addAll(data);
        notifyDataSetChanged();
    }

    public void adddata(List<Good.NewslistBean> data) {
        mRxxp.addAll(data);
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
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, final int i) {


        subViewHolder.tvOne.setText(mRxxp.get(i).getTitle());


        String url = mRxxp.get(i).getPicUrl();
        Uri parse = Uri.parse(url);
        subViewHolder.imgOne.setImageURI(parse);

        subViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mClickListener != null){
                    mClickListener.OnClick(i,mRxxp.get(i).getTitle());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRxxp.size();
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

    ClickListener mClickListener;

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {

        void OnClick(int i,String name);
    }

}
