package com.bashalex.cryptoinvesting.NewsScreen;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bashalex.cryptoinvesting.R;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;

/**
 * Created by bashalex on 26.01.16.
 */
@RequiresPresenter(NewsPresenter.class)
public class NewsFragment extends NucleusSupportFragment<NewsPresenter> {

    private static final String TAG = "NewsFragment";

    public SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton fab;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().activate(getContext());
        Log.d(TAG, "presenter initialised");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refreshContent();
            }
        });

        // Find Float Button
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setClickable(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().scrollToTop();
            }
        });
        fabHide();

        getPresenter().setAdapter(view.findViewById(R.id.news_list));
        Log.d(TAG, "adapter installed");
        return view;
    }

    public void fabHide() {
        fab.hide();
    }
    public void fabShow() {
        fab.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.setRefreshing(false);
    }
}
