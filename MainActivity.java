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


    // Create an {@link ArticleAdapter}, whose data source is a list of
    // {@link articles}. The adapter knows how to create list item views for each item
    // in the list.
    ArticleAdapter adapter = new ArticleAdapter(this, articles);

    // Get a reference to the ListView, and attach the adapter to the listView.
    ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


}


}
