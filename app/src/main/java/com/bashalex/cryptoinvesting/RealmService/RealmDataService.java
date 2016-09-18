package com.bashalex.cryptoinvesting.RealmService;

import android.content.Context;
import android.util.Log;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel.AltCoin;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by bashalex on 27.01.16.
 */
public class RealmDataService implements DataService {

    private static final String TAG = "RealmDataService";

    private final Context context;
    private Realm realm;

    public RealmDataService(Context context) {
        this.context = context;
        realm = Realm.getInstance(context);
    }

    private static AltCoin altCoinFromRealm(RealmAltCoin realmAltCoin) {
        final String name = realmAltCoin.getName();
        final String description = realmAltCoin.getDescription();
        final float price = realmAltCoin.getPrice();
        return new AltCoin(name, price, description);
    }

    @Override
    public Observable<AltCoin> getAltCoins() {
        return realm.where(RealmAltCoin.class)
                .findAllAsync()
                .asObservable()
                .flatMap(new Func1<RealmResults<RealmAltCoin>, Observable<RealmAltCoin>>() {
                    @Override
                    public Observable<RealmAltCoin> call(RealmResults<RealmAltCoin> realmAltCoins) {
                        return Observable.from(realmAltCoins);
                    }
                })
                .map(new Func1<RealmAltCoin, AltCoin>() {
                    @Override
                    public AltCoin call(RealmAltCoin realmAltCoin) {
                        return altCoinFromRealm(realmAltCoin);
                    }
                });
    }

    @Override
    public void newAltCoin(String name, float price, String description) {
        realm.beginTransaction();
        final RealmAltCoin realmAltCoin = new RealmAltCoin();
        realmAltCoin.setName(name);
        realmAltCoin.setPrice(price);
        realmAltCoin.setDescription(description);
        realm.copyToRealm(realmAltCoin);
        realm.commitTransaction();
    }
}