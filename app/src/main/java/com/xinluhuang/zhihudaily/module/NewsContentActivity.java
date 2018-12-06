package com.xinluhuang.zhihudaily.module;

import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinluhuang.zhihudaily.util.Constants;
import com.xinluhuang.zhihudaily.R;
import com.xinluhuang.zhihudaily.app.ZHApplication;
import com.xinluhuang.zhihudaily.retrofit.bean.NewsContentBean;
import com.xinluhuang.zhihudaily.retrofit.RetrofitHelper;
import com.xinluhuang.zhihudaily.retrofit.bean.NewsExtraBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsContentActivity extends AppCompatActivity {
    @BindView(R.id.wv)
    WebView webView;

    TextView tvComment;
    TextView tvThumb;
    ActionBar actionBar;
    int newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        initWebView();
        newsId = getIntent().getIntExtra(Constants.NEWS_ID, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RetrofitHelper.getZhihuApi().getContent(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsContentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ZHApplication.getContext(), "加载网页失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(NewsContentBean newsContentBean) {
                        StringBuilder htmlSb=new StringBuilder();
                        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";

                        htmlSb.append("<html><head>")
                                .append(css)
                                .append("</head><body>")
                                .append(newsContentBean.getBody())
                                .append("</body></html>");
                        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK )== Configuration.UI_MODE_NIGHT_YES) {
                            htmlSb.append("<script src=\"file:///android_asset/night.js\"></script>\n");
                        }
                        String html=htmlSb.toString();
                        html = html.replace("<div class=\"img-place-holder\">", "");
                        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //此方法在oncreate后调用
        getMenuInflater().inflate(R.menu.news_menu, menu);

        MenuItem itemComment = menu.findItem(R.id.item_comment);
        itemComment.setActionView(R.layout.item_comment);
        tvComment = itemComment.getActionView().findViewById(R.id.tv_comment);


        MenuItem itemThumb = menu.findItem(R.id.item_thumb);
        itemThumb.setActionView(R.layout.item_thumb);
        tvThumb = itemThumb.getActionView().findViewById(R.id.tv_thumb);

        initMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        // 调用js
        webView.addJavascriptInterface(this, "zhihuDaily");
    }

    private void initMenu() {
        RetrofitHelper.getZhihuApi().getExtra(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsExtraBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ZHApplication.getContext(), "加载评论失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(NewsExtraBean newsExtraBean) {
                        tvComment.setText(String.valueOf(newsExtraBean.getComments()));
                        tvThumb.setText(String.valueOf(newsExtraBean.getPopularity()));
                    }
                });
    }
}
