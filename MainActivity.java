package com.example.android.newsappstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create an ArrayList of Article objects
        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(new Article (getString(R.string.island_hop_your_way_around_scotlands_secret_arts_and_crafts_hubs),(getString(R.string.discover_the_uk)),(getString(R.string.june_2018)),(getString(R.string.laura_martin))));
        articles.add(new Article(getString(R.string.revolutionary_rodin_and_tahiti_before_Gauguin_the_week_in_art),(getString(R.string.art_and_design)), (getString(R.string.april_2018)), (getString(R.string.jonathan_jones))));
        articles.add(new Article(getString(R.string.the_pop_up_crafts_shop_helping_war_torn_communities),(getString(R.string.life_and_style)),(getString(R.string.november_2017)),(getString(R.string.ed_cumming))));


        // Create an {@link ArticleAdapter}, whose data source is a list of
    // {@link articles}. The adapter knows how to create list item views for each item
    // in the list.
    ArticleAdapter adapter = new ArticleAdapter(this, articles);

    // Get a reference to the ListView, and attach the adapter to the listView.
    ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


}


}
