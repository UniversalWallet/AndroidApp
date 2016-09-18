package com.bashalex.cryptoinvesting.TicketsScreen;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Alex Bash on 18.09.16.
 */

public interface TicketsAPI {

    @GET("listAll")
    Observable<TicketResponse> getAllTickets();

    @GET("verify")
    Observable<TicketResponse> verify();

}
