package com.bashalex.cryptoinvesting.TicketsScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bashalex.cryptoinvesting.R;

/**
 * Created by Alex Bash on 18.09.16.
 */

public class TicketsRecyclerViewAdapter extends RecyclerView.Adapter<TicketsRecyclerViewAdapter.ViewHolder> {

    private final TicketsPresenter.OnTicketsItemClickListener mListener;

    public TicketsRecyclerViewAdapter(TicketsPresenter.OnTicketsItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public TicketsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ticket, parent, false);
        return new TicketsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TicketsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = TicketsModel.TICKETS.get(position);
        holder.mName.setText(TicketsModel.TICKETS.get(position).getName());
        holder.mPrice.setText(TicketsModel.TICKETS.get(position).getPlace());
        holder.mPlace.setText(TicketsModel.TICKETS.get(position).getPrice());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onTicketsItemClick(holder.mItem, holder.mView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return TicketsModel.TICKETS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName, mPrice, mPlace;
        public TicketsModel.Ticket mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.ticket_name);
            mPlace = (TextView) view.findViewById(R.id.ticket_place);
            mPrice = (TextView) view.findViewById(R.id.ticket_price);
        }
    }
}
