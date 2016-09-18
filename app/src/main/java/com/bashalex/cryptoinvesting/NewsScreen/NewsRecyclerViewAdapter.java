package com.bashalex.cryptoinvesting.NewsScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bashalex.cryptoinvesting.NewsScreen.NewsModel.NewsItem;
import com.bashalex.cryptoinvesting.NewsScreen.NewsPresenter.OnNewsClickListener;
import com.bashalex.cryptoinvesting.R;
import com.squareup.picasso.Picasso;

/**
 * Created by bashalex on 26.01.16.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private final OnNewsClickListener mListener;
    private Context mContext;

    public NewsRecyclerViewAdapter(Context context, OnNewsClickListener listener) {
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = NewsModel.NEWS.get(position);
        holder.mTitleView.setText(NewsModel.NEWS.get(position).title);
        if (NewsModel.NEWS.get(position).title.length() > 50) {
            holder.mTitleView.setTextSize(18);
            if (NewsModel.NEWS.get(position).title.length() > 100) {
                holder.mTitleView.setText(
                        String.format("%s...", NewsModel.NEWS.get(position).title.substring(0, 100)));
            }
        } else {
            holder.mTitleView.setTextSize(22);
        }
        Picasso.with(mContext)
                .load(NewsModel.NEWS.get(position).imageUrl)
                .placeholder(mContext.getResources().getDrawable(R.drawable.reddit_bitcoin))
                .into(holder.mImageView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onNewsClick(holder.mItem, holder.mView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return NewsModel.NEWS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final ImageView mImageView;
        public NewsItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.news_title);
            mImageView = (ImageView) view.findViewById(R.id.news_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}