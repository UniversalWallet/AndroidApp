package com.bashalex.cryptoinvesting.AltCoinsListScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bashalex on 27.01.16.
 */
public class AltCoinsModel {

    public static List<AltCoin> ALTCOINS = new ArrayList<>();
    private static final String TAG = "ChatModel";

    public static void syncWithRemoteDB() {

    }

    public static class AltCoin {
        private String name;
        private float price;
        private String description;

        public AltCoin(String name, float price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
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

        @Override
        public String toString() {
            return name;
        }
    }
}
