package com.bashalex.cryptoinvesting.NewsScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bashalex.cryptoinvesting.FragmentPresenterInterface;
import com.bashalex.cryptoinvesting.NewsScreen.NewsModel.NewsItem;
import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.Child;
import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.Data_;
import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.RedditResponse;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.RxPresenter;
import retrofit.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by bashalex on 26.01.16.
 */
public class NewsPresenter extends RxPresenter<NewsFragment>
                            implements FragmentPresenterInterface {

    private static final String TAG = "NewsPresenter";

    private OnNewsClickListener mListener;
    private Subscription newsSubscription;

    private NewsRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private boolean updating;

    public NewsPresenter() {

    }

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Log.d(TAG, "onCreate()");
    }

    public void refreshContent() {
        getNews(10, false);
    }

    public void scrollToTop() {
        if (mLayoutManager.findFirstVisibleItemPosition() > 30) {
            recyclerView.scrollToPosition(0);
        } else {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void setAdapter(View view) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            mLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLayoutManager);
            adapter = new NewsRecyclerViewAdapter(context, mListener);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) {
                        // scrolled down
                        getView().fabHide();
                        int visibleItemCount = mLayoutManager.getChildCount();
                        int totalItemCount = mLayoutManager.getItemCount();
                        int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                        if (!updating) {
                            if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                getNews(5, true);
                            }
                        }
                    } else {
                        // scrolled up
                        if (dy < -50 && mLayoutManager.findFirstVisibleItemPosition() > 0) {
                            getView().fabShow();
                        }
                        if (mLayoutManager.findFirstVisibleItemPosition() == 0) {
                            getView().fabHide();
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public void activate(Context context) {
        initListeners(context);
        refreshContent();
    }

    private void getNews(int number, final boolean toTheEnd) {
        updating = true;
        newsSubscription = NewsModel.getNews(number, toTheEnd)
                .flatMap(new Func1<RedditResponse, Observable<NewsItem>>() {
                    @Override
                    public Observable<NewsItem> call(RedditResponse response) {
                        List<NewsItem> news = new ArrayList<>();
                        Log.d(TAG, "response: " + response.getData().toString());
                        List<Child> posts = response.getData().getChildren();
                        for (int i = 0; i < posts.size(); ++i) {
                            Data_ post = posts.get(i).getData();
                            news.add(new NewsItem(post.getTitle(),
                                    post.getName(),
                                    post.getThumbnail(),
                                    post.getUrl()));
                        }
                        return Observable.from(news);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsItem>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                        if (getView() == null) {
                            return;
                        }
                        getView().mRefreshLayout.setRefreshing(false);
                        updating = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                            Log.d(TAG, "error code: " + code);
                        } else {
                            Log.d(TAG, "error: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(NewsItem newsItem) {
                        if (!NewsModel.NEWS.contains(newsItem)) {
                            Log.d(TAG, "newsItem: " + newsItem.toString());
                            NewsModel.NEWS.add(toTheEnd ? NewsModel.NEWS.size() : 0, newsItem);
                            NewsModel.lastRedditPost = newsItem.fullname;
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "contains");
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        removeListeners();
        if (newsSubscription != null) {
            newsSubscription.unsubscribe();
        }
        adapter = null;
    }

    private void initListeners(Context context) {
        if (context instanceof OnNewsClickListener) {
            mListener = (OnNewsClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewsClickListener");
        }
    }

    private void removeListeners() {
        mListener = null;
    }

    public interface OnNewsClickListener {
        void onNewsClick(NewsItem news, View view);
    }
}
