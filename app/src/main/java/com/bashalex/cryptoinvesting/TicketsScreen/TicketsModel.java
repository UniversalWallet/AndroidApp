package com.bashalex.cryptoinvesting.TicketsScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Alex Bash on 18.09.16.
 */

public class TicketsModel {
    public static List<TicketsModel.Ticket> TICKETS = new ArrayList<>();
    private static final String TAG = "NotificationsModel";
    private static TicketsAPI ticketsAPIInterface;

    public static void syncWithRemoteDB() {

    }

    public static Observable<TicketResponse> getAllTickets() {
        return getClient().getAllTickets();
    }
    public static void verify() {
        getClient().verify().subscribe();
    }


    private static TicketsAPI getClient() {
        if (ticketsAPIInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl("http://inn.jwma.ru/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            ticketsAPIInterface = client.create(TicketsAPI.class);
        }
        return ticketsAPIInterface;
    }

    public static class Ticket {
        private String name;
        private String place;
        private String price;

        public Ticket(String name, String place, String price) {
            this.name = name;
            this.price = price;
            this.place = place;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "name='" + name + '\'' +
                    ", place='" + place + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
