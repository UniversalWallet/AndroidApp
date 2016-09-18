package com.bashalex.cryptoinvesting;

import android.content.Context;
import android.view.View;

import nucleus.presenter.Presenter;

/**
 * Created by bashalex on 26.01.16.
 */
public interface FragmentPresenterInterface {

    void setAdapter(View view);

    void activate(Context context);

}
