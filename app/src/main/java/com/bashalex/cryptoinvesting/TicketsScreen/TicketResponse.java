package com.bashalex.cryptoinvesting.TicketsScreen;
import com.google.gson.JsonArray;


/**
 * Created by Alex Bash on 18.09.16.
 */

public class TicketResponse {
    private JsonArray data;

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TicketResponse{" +
                "data=" + data +
                '}';
    }
}
