package com.bashalex.cryptoinvesting;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsModel;
import com.bashalex.cryptoinvesting.AltCoinsListScreen.AltCoinsPresenter;
import com.bashalex.cryptoinvesting.NewsScreen.NewsModel;
import com.bashalex.cryptoinvesting.NewsScreen.NewsPresenter;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsModel;
import com.bashalex.cryptoinvesting.NotificationsScreen.NotificationsPresenter;
import com.bashalex.cryptoinvesting.TicketsScreen.TicketsModel;
import com.bashalex.cryptoinvesting.TicketsScreen.TicketsPresenter;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusAppCompatActivity<MainPresenter> implements
        AltCoinsPresenter.OnAltCoinItemClickListener, NewsPresenter.OnNewsClickListener,
        TicketsPresenter.OnTicketsItemClickListener, NotificationsPresenter.OnNotificationsItemClickListener {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        AltCoinsModel.ALTCOINS.add(new AltCoinsModel.AltCoin("Основной", 1, "Основной"));
        AltCoinsModel.ALTCOINS.add(new AltCoinsModel.AltCoin("Семейный", 2, "Семейный"));
//        TicketsModel.TICKETS.add(new TicketsModel.Ticket("Сноуден", "place",  1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAltCoinItemClick(AltCoinsModel.AltCoin altcoin, View view) {
        Log.d(TAG, altcoin.toString());
        getPresenter().onAltCoinItemClicked(altcoin, view, this);
    }

    @Override
    public void onNewsClick(NewsModel.NewsItem news, View view) {
        Log.d(TAG, news.toString());
        getPresenter().onNewsClicked(news, view, this);
    }

    @Override
    public void onTicketsItemClick(TicketsModel.Ticket ticket, View view) {

    }

    @Override
    public void onNotificationsItemClick(TicketsModel.Ticket notification, View view) {
        TicketsModel.verify();
    }
}
