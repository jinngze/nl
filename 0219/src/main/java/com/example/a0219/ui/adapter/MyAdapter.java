package com.example.a0219.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a0219.R;
import com.example.a0219.data.bean.GoodsBean;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SubViewHolder> {


    private List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> listBeanXXES;
    private Context context;


    public MyAdapter(Context context) {
        this.context = context;
        listBeanXXES = new ArrayList<>();
    }


    public void setDatas(List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES) {
        if (commodityListBeanXXES != null) {
            listBeanXXES.addAll(commodityListBeanXXES);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.rxxp_item, null);
        SubViewHolder subViewHolder = new SubViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, final int i) {
        subViewHolder.name.setText(listBeanXXES.get(i).getCommodityName());
        subViewHolder.price.setText(listBeanXXES.get(i).getPrice()+"");
        String url = listBeanXXES.get(i).getMasterPic();
        Uri parse = Uri.parse(url);
        subViewHolder.imageView.setImageURI(parse);

        subViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mClickListener != null){
                    mClickListener.OnClick(i,listBeanXXES.get(i).getCommodityName());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeanXXES.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        SimpleDraweeView imageView;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
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
