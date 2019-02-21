package com.xinluhuang.zhihudaily.module.main;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xinluhuang.mylibrary.LoadMoreScrollListener;
import com.xinluhuang.mylibrary.base.BaseActivity;
import com.xinluhuang.mylibrary.util.SPUtil;
import com.xinluhuang.zhihudaily.R;
import com.xinluhuang.zhihudaily.module.NewsAdapter;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements  MainContract.IMainView{
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private ActionBar actionBar;
    private NewsAdapter newsAdapter;
    private int dayBefore=0;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setDayNight((Boolean) SPUtil.get(SPUtil.isDay, true));
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.main_title));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.addData();
            }
        });
        showLoading();
        mPresenter.refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_daynight:
                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (mode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SPUtil.put(SPUtil.isDay, true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SPUtil.put(SPUtil.isDay, false);
                }
                getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                recreate();
                break;
            default:
                break;
        }
        return true;
    }

    private void setDayNight(boolean isDay) {
        if (isDay) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }


    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        Toast.makeText(this,"加载错误",Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void refresh(LatestBean latestBean) {
        newsAdapter=new NewsAdapter(MainActivity.this, latestBean);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnScrollListener(new LoadMoreScrollListener() {
            @Override
            public void onLoadMore() {
                mPresenter.addData();
            }
        });
    }

    @Override
    public void addData(LatestBean latestBean) {
        newsAdapter.addData(latestBean);
        newsAdapter.notifyDataSetChanged();
    }
}
