package com.example.a0222.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a0222.R;
import com.example.a0222.ui.activity.MainActivity;
import com.example.a0222.ui.adapter.LeftMenuAdapter;


public class LeftFragment extends Fragment {

    private ListView menus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_left_drawer, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menus = view.findViewById(R.id.menus);
        menus.setAdapter(new LeftMenuAdapter(getActivity()));

        menus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //图片 不做跳转
                if(position == 0) {
                    return;
                }

                //切换页面
                ((MainActivity)getActivity()).showPage(position - 1);
            }
        });
    }
}
