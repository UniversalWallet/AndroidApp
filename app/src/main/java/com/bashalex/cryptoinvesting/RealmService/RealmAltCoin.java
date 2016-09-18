package com.bashalex.cryptoinvesting.RealmService;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bashalex on 27.01.16.
 */
public class RealmAltCoin extends RealmObject {

    @PrimaryKey
    private String name;
    private float price;
    private String description;

    public RealmAltCoin() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}