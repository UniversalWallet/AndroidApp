package com.bashalex.cryptoinvesting.TicketsScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bashalex.cryptoinvesting.R;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;

/**
 * Created by Alex Bash on 18.09.16.
 */

@RequiresPresenter(TicketsPresenter.class)
public class TicketsFragment extends NucleusSupportFragment<TicketsPresenter> {

    private static final String TAG = "ChatFragment";

    public TicketsFragment() {
    }

    public static TicketsFragment newInstance() {
        return new TicketsFragment();
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
        View view = inflater.inflate(R.layout.fragment_tickets_list, container, false);
        getPresenter().setAdapter(view);
        Log.d(TAG, "adapter installed");
        return view;
    }

}
