package com.bashalex.cryptoinvesting.AltCoinsListScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bashalex.cryptoinvesting.R;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;

@RequiresPresenter(AltCoinsPresenter.class)
public class AltCoinsFragment extends NucleusSupportFragment<AltCoinsPresenter> {

    private static final String TAG = "ChatFragment";

    public AltCoinsFragment() {
    }

    public static AltCoinsFragment newInstance() {
        return new AltCoinsFragment();
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
        View view = inflater.inflate(R.layout.fragment_altcoins_list, container, false);
        getPresenter().setAdapter(view);
        Log.d(TAG, "adapter installed");
        return view;
    }
}
