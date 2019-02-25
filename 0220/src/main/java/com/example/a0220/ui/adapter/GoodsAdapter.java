package com.example.a0220.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Good.NewslistBean> mRxxp;

    private Context mContext;

    public GoodsAdapter(Context mContext) {
        this.mContext = mContext;
        mRxxp = new ArrayList<>();

    }

    public void setDatas(List<Good.NewslistBean> commodityList) {
        if (commodityList != null) {
            mRxxp.addAll(commodityList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder mHolder = (ViewHolder) viewHolder;
        //mHolder.name.setText(mRxxp.get(i).getTitle());

           mHolder.tvOne.setText(mRxxp.get(i).getTitle());


        //mHolder.price.setText("￥"+mRxxp.get(i).getPrice());


        /* mHolder.imageView.setImageURI(mRxxp.get(i).getMasterPic());*/

    String url = mRxxp.get(i).getPicUrl();
        Uri parse = Uri.parse(url);
        mHolder.imgOne.setImageURI(parse);

       mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.OnClick(i, mRxxp.get(i).getTitle());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRxxp.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_one)
        SimpleDraweeView imgOne;
        @BindView(R.id.tv_one)
        TextView tvOne;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }

    ClickListener mClickListener;

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void OnClick(int i, String name);
    }

}
