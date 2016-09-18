package com.bashalex.cryptoinvesting.NotificationsScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bashalex.cryptoinvesting.R;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsModel;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsPresenter;
import com.bashalex.cryptoinvesting.TicketsScreen.TicketsModel;

/**
 * Created by Alex Bash on 18.09.16.
 */

public class NotificationsRecyclerViewAdapter extends RecyclerView.Adapter<NotificationsRecyclerViewAdapter.ViewHolder> {

    private final NotificationsPresenter.OnNotificationsItemClickListener mListener;

    public NotificationsRecyclerViewAdapter(NotificationsPresenter.OnNotificationsItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public NotificationsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notification, parent, false);
        return new NotificationsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = NotificationsModel.NOTIFICATIONS.get(position);
        holder.mName.setText(NotificationsModel.NOTIFICATIONS.get(position).getName());
        holder.mPrice.setText(NotificationsModel.NOTIFICATIONS.get(position).getPrice());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onNotificationsItemClick(holder.mItem, holder.mView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return NotificationsModel.NOTIFICATIONS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName, mPrice;
        public TicketsModel.Ticket mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.not_name);
            mPrice = (TextView) view.findViewById(R.id.not_price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
