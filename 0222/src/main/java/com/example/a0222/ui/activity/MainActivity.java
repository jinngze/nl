package com.example.a0222.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.a0222.R;
import com.example.a0222.fragment.LeftFragment;
import com.example.a0222.ui.adapter.MainPageAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ViewPager contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为空才添加Fragment: 左边菜单s
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.left_drawer, new LeftFragment())
                    .commit();
        }

        //初始化View
        initView();

        //加载数据
        initData();

    }

    private void initData() {
    }

    private void initView() {
        //允许标题栏展示左边icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer);

        //actionbar 和 DrawerLahyout中间人, 把中间人创建出来
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        contents = findViewById(R.id.contents);

        //绑定adapter
        contents.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.bottom_indicator);
        //创建底部三个tab
        //tabLayout.addTab();
        //tabLayout.addTab();
        //tabLayout.addTab();
        //1 adapter里返回标题
        //2 根据ViewPager设置TabLayout
        tabLayout.setupWithViewPager(contents);
        //tabLayout.getTabAt(0).setText("首页");
        //tabLayout.getTabAt(1).setText("视频");
        //tabLayout.getTabAt(2).setText("我的");
        //tabLayout.getTabAt(0).setIcon();
    }

    //显示具体页面  0 - 3
    public void showPage(int position) {
        contents.setCurrentItem(position);
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
