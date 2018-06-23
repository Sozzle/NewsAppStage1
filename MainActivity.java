package com.example.android.newsappstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** URL to query the Guardian dataset for artamdcrafts information */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=Art%20and%20Crafts&api-key=f5c30d8e-0b2b-4486-afd6-e1583d0520f1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//Get the list of earthquakes from {@link QueryUtils}
        ArrayList<Article> articles = QueryUtils.extractArticles();
        // Create an {@link ArticleAdapter}, whose data source is a list of
    // {@link articles}. The adapter knows how to create list item views for each item
    // in the list.
    ArticleAdapter adapter = new ArticleAdapter(this, articles);

    // Get a reference to the ListView, and attach the adapter to the listView.
    ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


}


}
