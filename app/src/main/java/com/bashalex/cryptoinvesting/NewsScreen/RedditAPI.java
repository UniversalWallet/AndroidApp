package com.bashalex.cryptoinvesting.NewsScreen;

import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.RedditResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by bashalex on 26.01.16.
 */
public interface RedditAPI {
    @GET("r/ethereum/hot.json?sort=new")
    Observable<RedditResponse> getNewBitcoinPosts(@Query("limit") int number);

    @GET("r/ethereum/hot.json?sort=new")
    Observable<RedditResponse> getNewBitcoinPosts(@Query("limit") int number,
                                                  @Query("after") String lastPost);
}
