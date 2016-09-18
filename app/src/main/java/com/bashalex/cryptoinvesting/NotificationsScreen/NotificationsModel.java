package com.bashalex.cryptoinvesting.NotificationsScreen;

import com.bashalex.cryptoinvesting.TicketsScreen.TicketsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Bash on 18.09.16.
 */

public class NotificationsModel {
    public static List<TicketsModel.Ticket> NOTIFICATIONS = new ArrayList<>();
    private static final String TAG = "NotificationsModel";

    public static void syncWithRemoteDB() {

    }

    public static class Notification {
        private String name;
        private int price;

        public Notification(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return name;
        }

    }
}
