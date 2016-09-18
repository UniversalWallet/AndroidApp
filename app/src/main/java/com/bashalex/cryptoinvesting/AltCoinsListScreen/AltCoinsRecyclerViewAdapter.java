package com.bashalex.cryptoinvesting.AltCoinsListScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bashalex.cryptoinvesting.R;
import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsPresenter.OnAltCoinItemClickListener;

public class AltCoinsRecyclerViewAdapter extends RecyclerView.Adapter<AltCoinsRecyclerViewAdapter.ViewHolder> {

    private final OnAltCoinItemClickListener mListener;

    public AltCoinsRecyclerViewAdapter(OnAltCoinItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_altcoin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = AltCoinsModel.ALTCOINS.get(position);
        holder.mContentView.setText(AltCoinsModel.ALTCOINS.get(position).getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onAltCoinItemClick(holder.mItem, holder.mView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return AltCoinsModel.ALTCOINS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public AltCoinsModel.AltCoin mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.altcoin_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
