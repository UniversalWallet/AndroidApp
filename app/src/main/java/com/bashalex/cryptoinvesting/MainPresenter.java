package com.bashalex.cryptoinvesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel;
import com.bashalex.cryptoinvesting.NewsScreen.NewsModel;

import nucleus.presenter.Presenter;

/**
 * Created by bashalex on 26.01.16.
 */
public class MainPresenter extends Presenter<MainActivity>
        implements MainPresenterInterface {

    private static final String TAG = "MainPresenter";

    private Bundle makeAnimation(View view, Activity activity) {
        Pair<View, String> holderPair = Pair.create(view, "tHolder");
        return ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, holderPair)
                .toBundle();
    }

    @Override
    public void onAltCoinItemClicked(AltCoinsModel.AltCoin altcoin,
                                     View view, Activity activity) {
        Log.d(TAG, "onAltCoinClicked()");
//        Intent intent = new Intent(activity, AltCoinInfo.class);
//        intent.putExtra(activity.getString(R.string.name), altcoin.getName());
//        ActivityCompat.startActivity(activity, intent, makeAnimation(view, activity));
    }

    @Override
    public void onNewsClicked(NewsModel.NewsItem news, View view, Activity activity) {
        Log.d(TAG, "onNewsClicked()");
        Intent intent = new Intent(activity, NewsActivity.class);
        intent.putExtra(activity.getString(R.string.url), news.url);
        ActivityCompat.startActivity(activity, intent, makeAnimation(view, activity));
    }
}
