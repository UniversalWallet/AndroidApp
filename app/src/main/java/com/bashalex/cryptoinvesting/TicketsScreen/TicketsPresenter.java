package com.bashalex.cryptoinvesting.TicketsScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel;
import com.bashalex.cryptoinvesting.FragmentPresenterInterface;
import com.bashalex.cryptoinvesting.NewsScreen.NewsModel;
import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.Child;
import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.Data_;
import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.RedditResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.Presenter;
import retrofit.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Alex Bash on 18.09.16.
 */

public class TicketsPresenter extends Presenter<TicketsFragment>
        implements FragmentPresenterInterface {

    private static final String TAG = "ChatPresenter";

    private TicketsPresenter.OnTicketsItemClickListener mListener;
    private Subscription altCoinsSubscription;
    private TicketsRecyclerViewAdapter adapter;
    private Subscription newsSubscription;
    private boolean updating;

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
            adapter = new TicketsRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void activate(Context context) {
        initListener(context);
        syncWithRemoteDB();
        getAllTickets();
    }

    private void syncWithRemoteDB() {
        AltCoinsModel.syncWithRemoteDB();
    }

    private void getAllTickets() {
        updating = true;
        newsSubscription = TicketsModel.getAllTickets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TicketResponse>() {
                    @Override
                    public void onCompleted() {
                        updating = false;
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TicketResponse ticketResponse) {
                        for (int i = 0; i < ticketResponse.getData().size(); ++i) {
                            JsonObject ticket = ticketResponse.getData().get(i).getAsJsonObject();
                            Log.d(TAG, "ticketResponse: " + ticketResponse);
                            Log.d(TAG, "ticket: " + ticket);
                            Log.d(TAG, "here!");
                            TicketsModel.Ticket t = new TicketsModel.Ticket(ticket.get("event").getAsString(),
                                    ticket.get("place").getAsString(), ticket.get("price").getAsString());
                            Log.d(TAG, "ticket: " + t);
                            TicketsModel.TICKETS.add(t);
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeListener();
        if (altCoinsSubscription != null) {
            altCoinsSubscription.unsubscribe();
        }
    }

    public void initListener(Context context) {
        if (context instanceof TicketsPresenter.OnTicketsItemClickListener) {
            mListener = (TicketsPresenter.OnTicketsItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement TicketsItemClickListener");
        }
    }

    public void removeListener() {
        mListener = null;
    }

    public interface OnTicketsItemClickListener {
        void onTicketsItemClick(TicketsModel.Ticket ticket, View view);
    }
}
