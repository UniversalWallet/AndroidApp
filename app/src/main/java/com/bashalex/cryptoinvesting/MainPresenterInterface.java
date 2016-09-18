package com.bashalex.cryptoinvesting;

import android.app.Activity;
import android.view.View;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel.AltCoin;
import com.bashalex.cryptoinvesting.NewsScreen.NewsModel.NewsItem;

/**
 * Created by bashalex on 26.01.16.
 */
public interface MainPresenterInterface {

    void onAltCoinItemClicked(AltCoin altcoin, View view, Activity activity);

    void onNewsClicked(NewsItem altcoin, View view, Activity activity);

}
