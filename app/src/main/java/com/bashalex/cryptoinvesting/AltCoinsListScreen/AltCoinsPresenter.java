package com.bashalex.cryptoinvesting.AltCoinsListScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel.AltCoin;
import com.bashalex.cryptoinvesting.FragmentPresenterInterface;
import com.bashalex.cryptoinvesting.RealmService.RealmDataService;

import nucleus.presenter.Presenter;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by bashalex on 26.01.16.
 */
public class AltCoinsPresenter extends Presenter<AltCoinsFragment>
        implements FragmentPresenterInterface {

    private static final String TAG = "ChatPresenter";

    private OnAltCoinItemClickListener mListener;
    private RealmDataService localDB;
    private Subscription altCoinsSubscription;
    private AltCoinsRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
    }

    @Override
    public void setAdapter(View view) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new AltCoinsRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void activate(Context context) {
        initListener(context);
        localDB = new RealmDataService(context);
        Log.d(TAG, "activate()");
        syncWithRemoteDB();
        getAltCoins();
    }

    private void syncWithRemoteDB() {
        AltCoinsModel.syncWithRemoteDB();
    }

    private void getAltCoins() {
        altCoinsSubscription = localDB.getAltCoins()
                .subscribe(new Action1<AltCoin>() {
                    @Override
                    public void call(AltCoin altCoin) {
                        Log.d(TAG, "call " + altCoin.toString());
                        AltCoinsModel.ALTCOINS.add(altCoin);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localDB = null;
        removeListener();
        if (altCoinsSubscription != null) {
            altCoinsSubscription.unsubscribe();
        }
    }

    public void initListener(Context context) {
        if (context instanceof OnAltCoinItemClickListener) {
            mListener = (OnAltCoinItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAltCoinItemClickListener");
        }
    }

    public void removeListener() {
        mListener = null;
    }

    public interface OnAltCoinItemClickListener {
        void onAltCoinItemClick(AltCoin altcoin, View view);
    }
}
