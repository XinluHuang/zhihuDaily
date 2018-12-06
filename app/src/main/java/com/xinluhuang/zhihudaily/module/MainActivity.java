package com.xinluhuang.zhihudaily.module;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xinluhuang.zhihudaily.retrofit.ZhihuApi;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;
import com.xinluhuang.zhihudaily.R;
import com.xinluhuang.zhihudaily.retrofit.RetrofitHelper;
import com.xinluhuang.zhihudaily.util.DateUtil;
import com.xinluhuang.zhihudaily.util.LogUtil;
import com.xinluhuang.zhihudaily.util.SPUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private ActionBar actionBar;
    private NewsAdapter newsAdapter;
    private int dayBefore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDayNight((Boolean) SPUtil.get(SPUtil.isDay, true));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.main_title));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swipeRefreshLayout.setColorSchemeColors(getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();
    }
    private void addData(){
        RetrofitHelper.getZhihuApi()
                .getBeforeList(DateUtil.getDayBefore(++dayBefore))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LatestBean latestBean) {
                        newsAdapter.addData(latestBean);
                        newsAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void refresh() {
        RetrofitHelper.getZhihuApi()
                .getLatestList()
//                .getBeforeList(DateUtil.getToday())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LatestBean latestBean) {
                        //不能放在外面，否则先setAdapter，再网络加载
                        newsAdapter=new NewsAdapter(MainActivity.this, latestBean);
                        recyclerView.setAdapter(newsAdapter);
                        addData();
                        recyclerView.addOnScrollListener(new LoadMoreScrollListener() {
                            @Override
                            public void onLoadMore() {
                                addData();
                            }
                        });
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
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
}
