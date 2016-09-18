package com.bashalex.cryptoinvesting.NewsScreen;

import com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels.RedditResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by bashalex on 26.01.16.
 */
public class NewsModel {

    private static RedditAPI redditAPIInterface;
    public static List<NewsItem> NEWS = new ArrayList<>();
    private static final String TAG = "NewsModel";
    public static String lastRedditPost;

    public static Observable<RedditResponse> getNews(int number, boolean toTheEnd) {
        if (toTheEnd && lastRedditPost != null) {
            return getRedditClient().getNewBitcoinPosts(number, lastRedditPost);
        }
        return getRedditClient().getNewBitcoinPosts(number);
    }

    private static RedditAPI getRedditClient() {
        if (redditAPIInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            redditAPIInterface = client.create(RedditAPI.class);
        }
        return redditAPIInterface;
    }

    public static class NewsItem {
        public final String title;
        public final String fullname;
        public final String imageUrl;
        public final String url;

        public NewsItem(String title, String fullname, String imageUrl, String url) {
            this.title = title;
            this.fullname = fullname;
            this.imageUrl = imageUrl;
            this.url = url;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final NewsItem other = (NewsItem) obj;
            return (this.url == null) ? other.url == null : this.url.equals(other.url);
        }

        @Override
        public int hashCode() {
            return 1234;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
