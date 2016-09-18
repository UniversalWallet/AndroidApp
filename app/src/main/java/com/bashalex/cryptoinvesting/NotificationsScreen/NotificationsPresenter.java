package com.bashalex.cryptoinvesting.NotificationsScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel;
import com.bashalex.cryptoinvesting.FragmentPresenterInterface;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsFragment;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsModel;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsRecyclerViewAdapter;
import com.bashalex.cryptoinvesting.TicketsScreen.TicketResponse;
import com.bashalex.cryptoinvesting.TicketsScreen.TicketsModel;
import com.google.gson.JsonObject;

import nucleus.presenter.Presenter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alex Bash on 18.09.16.
 */

public class NotificationsPresenter extends Presenter<NotificationsFragment>
        implements FragmentPresenterInterface {

    private static final String TAG = "NotificationsPresenter";

    private NotificationsPresenter.OnNotificationsItemClickListener mListener;
    private Subscription altCoinsSubscription;
    private NotificationsRecyclerViewAdapter adapter;
    private Subscription newsSubscription;

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
            adapter = new NotificationsRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);
        }
    }

    private void getNotifications() {
        newsSubscription = TicketsModel.getAllTickets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TicketResponse>() {
                    @Override
                    public void onCompleted() {
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
                            NotificationsModel.NOTIFICATIONS.add(t);
                        }
                    }
                });
    }

    @Override
    public void activate(Context context) {
        initListener(context);
        syncWithRemoteDB();
        getNotifications();
    }

    private void syncWithRemoteDB() {
        AltCoinsModel.syncWithRemoteDB();
    }

    private void getAltNotifications() {

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
        if (context instanceof NotificationsPresenter.OnNotificationsItemClickListener) {
            mListener = (NotificationsPresenter.OnNotificationsItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NotificationsItemClickListener");
        }
    }

    public void removeListener() {
        mListener = null;
    }

    public interface OnNotificationsItemClickListener {
        void onNotificationsItemClick(TicketsModel.Ticket notification, View view);
    }
}
