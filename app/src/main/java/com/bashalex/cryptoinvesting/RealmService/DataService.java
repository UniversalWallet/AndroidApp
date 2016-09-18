package com.bashalex.cryptoinvesting.RealmService;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel.AltCoin;


import rx.Observable;

/**
 * Created by bashalex on 27.01.16.
 */
public interface DataService {
    Observable<AltCoin> getAltCoins();
    void newAltCoin(String name, float price, String description);
}
